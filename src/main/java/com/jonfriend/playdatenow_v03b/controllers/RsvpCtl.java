package com.jonfriend.playdatenow_v03b.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.jonfriend.playdatenow_v03b.models.RsvpMdl;
import com.jonfriend.playdatenow_v03b.models.PlaydateMdl;
import com.jonfriend.playdatenow_v03b.models.UserMdl;
import com.jonfriend.playdatenow_v03b.services.RsvpSrv;
import com.jonfriend.playdatenow_v03b.services.PlaydateSrv;
import com.jonfriend.playdatenow_v03b.services.UserSrv;

@Controller
//public class RsvpCtl {
public class RsvpCtl {

	@Autowired
	private RsvpSrv rsvpSrv;
	
	@Autowired
	private UserSrv userSrv;
	
	@Autowired
	private PlaydateSrv playdateSrv;
	
	// JRF: THINK WE CAN TAKE THIS OUT NOW, will delete after app successfully deployed for first time
	// display create-new page
	@GetMapping("/playdate/{id}/rsvp/new")
	public String newRsvp(
			@PathVariable ("id") Long playdateId
			, @ModelAttribute("rsvp") RsvpMdl rsvpMdl
			, Model model
			, HttpSession session
			) {
		 
		// log out the unauth / deliver the auth user data
		if(session.getAttribute("userId") == null) {return "redirect:/logout";}
		Long authenticatedUserId = (Long) session.getAttribute("userId");
		model.addAttribute("authUser", userSrv.findById(authenticatedUserId));
		
		// getting the parent record from the pathvariable
		PlaydateMdl playdateObj = playdateSrv.findById(playdateId);
		// sending that parent record to the page
		model.addAttribute("playdate", playdateObj);
		// placeholder for getting/sending list of already created rsvp

		return "rsvp/create.jsp"; 
	}
	
	// JRF: DEF NEED THIS ONE!!!
	// process the create-new  
	@PostMapping("/playdate/{id}/rsvp/create")
	public String addNewRsvp(
			@PathVariable ("id") Long playdateId
			, @Valid @ModelAttribute("rsvp") RsvpMdl rsvpMdl
			, BindingResult result
			, Model model
			, HttpSession session
			) {
		
		// log out the unauth / deliver the auth use data
		if(session.getAttribute("userId") == null) {return "redirect:/logout";}
		Long authenticatedUserId = (Long) session.getAttribute("userId");
		model.addAttribute("authUser", userSrv.findById(authenticatedUserId));
		
		// below gets us the playdate object by using incoming path variable 
		PlaydateMdl playdateObj = playdateSrv.findById(playdateId);
		
		if(result.hasErrors()) {
			
			// sending the parent record to the page
			model.addAttribute("playdate", playdateObj);
			
//			return "rsvp/create.jsp";
			return "playdate/record.jsp";
		} else {
			
			// first... get current user whole object, for infusion into rsvp record
			UserMdl currentUserMdl = userSrv.findById(authenticatedUserId); 
			
			// first, instantiate the new object
			RsvpMdl newOtc = new RsvpMdl(); // ... and next: infuse into that object all the values from the incoming model/form
			newOtc.setPlaydateMdl(playdateObj); // parent record
			newOtc.setUserMdl(currentUserMdl); // user that is creating it
			newOtc.setRsvpStatus(rsvpMdl.getRsvpStatus()); 
			newOtc.setKidCount(rsvpMdl.getKidCount()); 
			newOtc.setAdultCount(rsvpMdl.getAdultCount()); 
			newOtc.setComment(rsvpMdl.getComment());

			rsvpSrv.create(newOtc);
			
			return "redirect:/playdate/" + playdateId ;
		}
	} 
	
	// JRF: THINK WE CAN TAKE THIS OUT NOW, will delete after app successfully deployed for first time
	// view record
	@GetMapping("/rsvp/{id}")
	public String showRsvp(
			@PathVariable("id") Long rsvpId
			, Model model
			, HttpSession session
			) {
		
		// log out the unauth / deliver the auth use data
		if(session.getAttribute("userId") == null) {return "redirect:/logout";}
		Long AuthenticatedUserId = (Long) session.getAttribute("userId");
		model.addAttribute("authUser", userSrv.findById(AuthenticatedUserId));
		
		RsvpMdl rsvpObj = rsvpSrv.findById(rsvpId); // get the object that is the primary object displayed on this page
		PlaydateMdl playdateObj = rsvpObj.getPlaydateMdl(); // get the object that is the parent to the primary object
		
		model.addAttribute("rsvp", rsvpObj); // deliver the object that is the primary object on this page 
		model.addAttribute("playdate", playdateObj);   // deliver the object that is the parent to the primary object on this page
		
		return "rsvp/record.jsp";
	}

	// JRF: THIS IS ABSOLUTELY NEEDED!  The code style section makes it look old school, but this is actually delivering the editRsvp interface on a page that contains the playdate info
	// display edit page
	@GetMapping("rsvp/{rsvpId}/edit")
	public String editRsvp(
			@PathVariable("rsvpId") Long rsvpId
			, Model model
			, HttpSession session
			) {
		
		// log out the unauth / deliver the auth user data
		if(session.getAttribute("userId") == null) {return "redirect:/logout";}
		Long authenticatedUserId = (Long) session.getAttribute("userId");
		model.addAttribute("authUser", userSrv.findById(authenticatedUserId));
		
		UserMdl currentUserMdl = userSrv.findById(authenticatedUserId); // used for show/not edit button
		RsvpMdl rsvpObj = rsvpSrv.findById(rsvpId); // get the object that is the primary object displayed on this page
		PlaydateMdl playdateObj = rsvpObj.getPlaydateMdl(); // get the object that is the parent to the primary object
		List<RsvpMdl> rsvpList = rsvpSrv.returnAllRsvpForPlaydate(playdateObj);
		
		// start: fun with functions & loops
			Integer rsvpCount = rsvpList.size(); // get a count of how many items in the list
			Integer sumRsvpDotKidsCount = 0; // instantiate the java variable that we will update in the loop 
			Boolean rsvpExistsCreatedByAuthUser = false; // instantiate the java variable that we will update in the loop
			
			Long rsvpIdForAuthUser = (long) 0; 
			
			Integer sumRsvpDotAdultsCount = 0; // instantiate the java variable that we will update in the loop
			Integer openKidsSpots = 0; // instantiate the java variable that we will update in the loop
			
			for (int i=0; i < rsvpCount; i++  ) {
				System.out.println("RSVP #" + i +  " (" + rsvpList.get(i).getUserMdl().getUserName() + "): " + rsvpList.get(i).getKidCount() + " (" + rsvpList.get(i).getRsvpStatus() + ")"  ); 
				
	//			sumRsvpDotKidsCount += rsvpList.get(i).getKidCount();
				// JRF: line above replaced by if/else below.   let's see how it goes
				if ( rsvpList.get(i).getRsvpStatus().equals("In")) {
					sumRsvpDotKidsCount = sumRsvpDotKidsCount + rsvpList.get(i).getKidCount(); 
				} else {
					sumRsvpDotKidsCount = sumRsvpDotKidsCount + 0; 
				}
				
				if ( rsvpList.get(i).getRsvpStatus().equals("In")) {
					sumRsvpDotAdultsCount = sumRsvpDotAdultsCount + rsvpList.get(i).getAdultCount(); 
				} else {
					sumRsvpDotAdultsCount = sumRsvpDotAdultsCount + 0; 
				} 
				
				if (rsvpList.get(i).getUserMdl().equals(currentUserMdl) )
				{
					rsvpExistsCreatedByAuthUser = true; // if there's a a match, set to true.  that's it, that's all you gotta do. 
					rsvpIdForAuthUser = rsvpList.get(i).getId(); 
				}   
			}
			
			openKidsSpots = playdateObj.getMaxCountKids() - sumRsvpDotKidsCount;
			
			RsvpMdl rsvpObjForAuthUser = rsvpSrv.findById(rsvpIdForAuthUser); 
		// end: fun with functions & loops
		
		model.addAttribute("playdate", playdateObj);   // deliver the object that is the parent to the primary object on this page
		model.addAttribute("rsvpCount", rsvpCount); 
		model.addAttribute("sumRsvpDotKidsCount", sumRsvpDotKidsCount); 
		model.addAttribute("rsvpExistsCreatedByAuthUser", rsvpExistsCreatedByAuthUser);
		model.addAttribute("rsvpObjForAuthUser", rsvpObjForAuthUser); 
		model.addAttribute("rsvp", rsvpObj); // deliver the object that is the primary object on this page
		model.addAttribute("rsvpList", rsvpList);
		model.addAttribute("sumRsvpDotAdultsCount", sumRsvpDotAdultsCount); 
		
		model.addAttribute("openKidsSpots", openKidsSpots); 

		return "rsvp/edit.jsp";
	}
	

	// JRF: DEF NEED THIS ONE
	// process the edit
	@PostMapping("/rsvp/edit")
	public String PostTheEditRsvp(
//			@PathVariable ("id") Long playdateId
			@Valid @ModelAttribute("rsvp") RsvpMdl rsvpMdl
			, BindingResult result
			, Model model
			, HttpSession session
			, RedirectAttributes redirectAttributes
			) {
		
		// log out the unauth / deliver the auth use data
		if(session.getAttribute("userId") == null) {return "redirect:/logout";}
		Long authenticatedUserId = (Long) session.getAttribute("userId");
		model.addAttribute("authUser", userSrv.findById(authenticatedUserId));
		
		// below now setting up rthe rsvp object by using the getID on the modAtt thing. 
		RsvpMdl rsvpObj = rsvpSrv.findById(rsvpMdl.getId());
		PlaydateMdl playdateObj = rsvpObj.getPlaydateMdl(); // get the object that is the parent to the primary object
		
		Long rsvpID = rsvpObj.getId(); 
		Long playdateId = playdateObj.getId(); 
		
		UserMdl currentUserMdl = userSrv.findById(authenticatedUserId); //  gets the userModel object by calling the user service with the session user id
		UserMdl rsvpCreatorUserMdl = rsvpObj.getUserMdl();   // gets the userMdl obj saved to the existing playdateObj
		
		List<RsvpMdl> rsvpList = rsvpSrv.returnAllRsvpForPlaydate(playdateObj); // 812
		
		if(!currentUserMdl.equals(rsvpCreatorUserMdl)) {
			redirectAttributes.addFlashAttribute("permissionErrorMsg", "That RSVP can only be edited by its creator.  Any edits just attempted were discarded.");
			return "redirect:/playdate/" + playdateObj.getId();
		}
		
		if(result.hasErrors()) {
			
			// start: fun with functions & loops
				Integer rsvpCount = rsvpList.size(); // get a count of how many items in the list
				Integer sumRsvpDotKidsCount = 0; // instantiate the java variable that we will update in the loop 
				Boolean rsvpExistsCreatedByAuthUser = false; // instantiate the java variable that we will update in the loop
				
				Long rsvpIdForAuthUser = (long) 0; 
				
				for (int i=0; i < rsvpCount; i++  ) {
					System.out.println("RSVP #" + i +  " (" + rsvpList.get(i).getUserMdl().getUserName() + "): " + rsvpList.get(i).getKidCount() + " (" + rsvpList.get(i).getRsvpStatus() + ")"  ); 
					
	//				sumRsvpDotKidsCount += rsvpList.get(i).getKidCount();
					// JRF: line above replaced by if/else below.   let's see how it goes
					if ( rsvpList.get(i).getRsvpStatus().equals("In")) {
						sumRsvpDotKidsCount = sumRsvpDotKidsCount + rsvpList.get(i).getKidCount(); 
					} else {
						sumRsvpDotKidsCount = sumRsvpDotKidsCount + 0; 
					}
					
					System.out.println("sum: " + sumRsvpDotKidsCount); 
					
					if (rsvpList.get(i).getUserMdl().equals(currentUserMdl) )
					{
						rsvpExistsCreatedByAuthUser = true; // if there's a a match, set to true.  that's it, that's all you gotta do. 
						rsvpIdForAuthUser = rsvpList.get(i).getId(); 
					}   
				}
				
//				RsvpMdl rsvpObjForAuthUser = rsvpSrv.findById(rsvpIdForAuthUser); 
			// end: fun with functions & loops
			
			model.addAttribute("playdate", playdateObj);   // deliver the object that is the parent to the primary object on this page
			model.addAttribute("rsvpCount", rsvpCount); 
			model.addAttribute("sumRsvpDotKidsCount", sumRsvpDotKidsCount); 
			model.addAttribute("rsvpExistsCreatedByAuthUser", rsvpExistsCreatedByAuthUser);
//			model.addAttribute("rsvpObjForAuthUser", rsvpObjForAuthUser); 
//			model.addAttribute("rsvp", rsvpObj); // deliver the object that is the primary object on this page
			model.addAttribute("rsvpList", rsvpList);
			// sending the parent record to the page

			
//			return "rsvp/create.jsp";
			return "rsvp/edit.jsp";
		} else {
			
			// actually, get the user object that created it in the first place, that's what we want to maintain
			UserMdl origCreatorUserMdl = rsvpObj.getUserMdl();  // get the object that is the parent to the primary object

			rsvpMdl.setUserMdl( origCreatorUserMdl);
			rsvpMdl.setPlaydateMdl(playdateObj); 
			rsvpSrv.update(rsvpMdl);
			
//			return "redirect:/playdate/" + playdateId + "/rsvp";
//			return "redirect:/rsvp/" + rsvpID;
			return "redirect:/playdate/" + playdateId;
			
			
		}
	} 
	
	// JRF: DEF NEED THIS ONE
	// delete rsvp
    @DeleteMapping("/rsvp/{id}")
    public String deleteRsvp(
    		@PathVariable("id") Long rsvpId
    		, HttpSession session
    		, RedirectAttributes redirectAttributes
    		) {
		// If no userId is found in session, redirect to logout.  JRF: put this on basically all methods now, except the login/reg pages
		if(session.getAttribute("userId") == null) {return "redirect:/logout";}
		Long authenticatedUserId = (Long) session.getAttribute("userId");

		RsvpMdl rsvpObj = rsvpSrv.findById(rsvpId);
		PlaydateMdl playdateObj = rsvpObj.getPlaydateMdl(); 
		Long playdateID = playdateObj.getId(); 
		
		UserMdl currentUserMdl = userSrv.findById(authenticatedUserId); //  gets the userModel object by calling the user service with the session user id
		UserMdl rsvpCreatorUserMdl = rsvpObj.getUserMdl();   // gets the userMdl obj saved to the existing playdateObj
		
		if(!currentUserMdl.equals(rsvpCreatorUserMdl)) {
			redirectAttributes.addFlashAttribute("permissionErrorMsg", "That RSVP can only be deleted by its creator.  RSVP not deleted.");
			return "redirect:/playdate/" + playdateObj.getId();
		}

		rsvpSrv.delete(rsvpObj);
        return "redirect:/playdate/" + playdateID;
    }
// end of methods
}
