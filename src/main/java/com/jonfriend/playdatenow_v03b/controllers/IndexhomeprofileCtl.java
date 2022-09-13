package com.jonfriend.playdatenow_v03b.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.jonfriend.playdatenow_v03b.models.LoginUserMdl;
//import com.jonfriend.playdatenow_v03b.models.PlaydateMdl;
//import com.jonfriend.playdatenow_v03b.models.StateterritoryMdl;
import com.jonfriend.playdatenow_v03b.models.UserMdl;
import com.jonfriend.playdatenow_v03b.models.UserupdateMdl;
//import com.jonfriend.playdatenow_v03b.services.StateterritorySrv;
import com.jonfriend.playdatenow_v03b.services.UserSrv;

@Controller
public class IndexhomeprofileCtl {
	
	@Autowired
	private UserSrv userSrv;
	
//	@Autowired
//	private StateterritorySrv stateterritorySrv;
	
	 
// ********************************************************************
// AUTHENTICATION METHODS
// ********************************************************************
	
	@GetMapping("/")
	public String index(
			Model model
			, HttpSession session) {
		
		// *** Redirect authorized users to the /home METHOD -- DON'T EXPOSE REG/LOGIN index page TO ALREADY AUTH'ED USERS ***
		if(session.getAttribute("userId") != null) {return "redirect:/home";}

		 
		model.addAttribute("newLogin", new LoginUserMdl()); // putting a new empty LoginUserMdl obj on the index page,
//        model.addAttribute("newUser", new UserMdl());  // login no longer on the same page as register
        
        System.out.println("Page Display: login"); 
		return "index.jsp"; 
	}
 
    @PostMapping("/login")
    public String login(
    		@Valid @ModelAttribute("newLogin") LoginUserMdl newLogin
    		, BindingResult result
    		, Model model
    		, HttpSession session
    		) {
    	
    	UserMdl user = userSrv.login(newLogin, result);
    	
        if(result.hasErrors() || user==null ) // user==null is the equiv of "user name not found!"
        {
        	model.addAttribute("newUser", new UserMdl()); //deliver the empty UserMdl object before re-rendering the reg/login page; the LoginUserMdl obj will maintain the incoming values to this method
        	model.addAttribute("validationErrorMsg", "Login errors.  See details in form below and try again.");
            return "index.jsp";
        }
    
        session.setAttribute("userId", user.getId()); // No errors?  Store the ID from the DB in session.
//	    return "redirect:/home";
	    return "redirect:/playdate"; // redirecting here to playdate for now, b/c insuff time to build out dashboard/home-style page
    }
     
	@GetMapping("/register")
	public String register(
			Model model
			, HttpSession session) {
		
		if(session.getAttribute("userId") != null) {return "redirect:/home";} // redirect authorized users to the /home METHOD; don't expose the index page to already-authenticated users

        model.addAttribute("newUser", new UserMdl()); // login/reg form items: putting a new empty UserMdl obj for on the index page, so user can shove data into it using the form.
//        model.addAttribute("newLogin", new LoginUserMdl()); // login no longer on the same page as register
        
        
        System.out.println("Page Display: Register"); 
		return "register.jsp"; 
	}
	
    @PostMapping("/register")
    public String register(
    		@Valid @ModelAttribute("newUser") UserMdl newUser
    		, BindingResult result
    		, Model model
    		, HttpSession session
    		, RedirectAttributes redirectAttributes
    		) {
        
    	UserMdl user = userSrv.register(newUser, result);
        
        if(result.hasErrors()) {
            // deliver the empty LoginUser object before re-rendering the reg/login page; the UserMdl obj will maintain the incoming values to this method
//            model.addAttribute("newLogin", new LoginUserMdl()); // this delivery of empty loginUser object is no longer needed, since login/reg on sep pages
        	model.addAttribute("validationErrorMsg", "Registration errors.  See details in form below and try again.");
            return "register.jsp";
        }
        
        session.setAttribute("userId", user.getId());  // this is a repeat of the last line of the login method
//	    return "redirect:/home";
        
        redirectAttributes.addFlashAttribute("successMsg", "WELCOME to PlayDateNow.  Take a moment to complete your profile: click on your name on the top right >> then click Profile.  Below: browse playdates and create your own.");
        
	    return "redirect:/playdate"; // redirecting here to playdate for now, b/c insuff time to build out dashboard/home-style page
    }
     
    @GetMapping("/logout")
	public String logout(
			HttpSession session
			) {
		// below nulls the session.userId value, which prevents access to any/all page(s) other than index, thus redirect to index. 
    	session.setAttribute("userId", null);
    	System.out.println("User logged out."); 
	    return "redirect:/";
	}

//********************************************************************
// HOME/PROFILE/ETC METHODS
//********************************************************************
		
	    @GetMapping("/home")
		public String home(
				Model model
				, HttpSession session
				) {
		 
	    	// log out the unauth vs. deliver the auth user data
			if(session.getAttribute("userId") == null) {return "redirect:/logout";}
			Long userId = (Long) session.getAttribute("userId");
			model.addAttribute("authUser", userSrv.findById(userId));
			
			System.out.println("Page Display: Home"); 
//		    return "home.jsp";  
		    return "redirect:/playdate"; // redirecting here to playdate for now, b/c insuff time to build out dashboard/home-style page
		}

		// view all record
		@GetMapping("/profile")
		public String showAllprofile(
//				@ModelAttribute("playdate") PlaydateMdl playdateMdl // this needed to display create-new on the page
				Model model
				, HttpSession session
				) {
			
			// log out the unauth / deliver the auth use data
			if(session.getAttribute("userId") == null) {return "redirect:/logout";}
			Long authenticatedUserId = (Long) session.getAttribute("userId");
			model.addAttribute("authUser", userSrv.findById(authenticatedUserId));
			
			List<UserMdl> profileList = userSrv.returnAll();
			model.addAttribute("profileList", profileList);

			return "profile/list.jsp";
		}
		
		// display user profile page
		@GetMapping("/profile/{id}")
		public String showProfile(
				@PathVariable("id") Long userProfileId
				, Model model
				, HttpSession session
				) {
			
	    	// log out the unauth vs. deliver the auth user data
			if(session.getAttribute("userId") == null) {return "redirect:/logout";}
			Long userId = (Long) session.getAttribute("userId");
			model.addAttribute("authUser", userSrv.findById(userId));
			
			// grab the entire user object using the url parameter, then deliver to page
			UserMdl userObj = userSrv.findById(userProfileId);
			model.addAttribute("userProfile", userObj); 
			
			System.out.println("Page Display: Profile"); 
			return "profile/record.jsp";
		}
		
		// display edit page
		@GetMapping("/profile/{id}/edit")
		public String editProfile(
				@ModelAttribute("userProfileTobe") UserupdateMdl userupdateMdl
				, @PathVariable("id") Long userProfileId
				, Model model
				, HttpSession session
				) {
			
			// log out the unauth / deliver the auth use data
			if(session.getAttribute("userId") == null) {return "redirect:/logout";}
			Long userId = (Long) session.getAttribute("userId");
			model.addAttribute("authUser", userSrv.findById(userId)); 
			
			
			
			// this delivers as-is userProfileObj, to initially populate the form fields 
			UserMdl userProfileObj = userSrv.findById(userProfileId); 
			
			userupdateMdl.setAboutMe(userProfileObj.getAboutMe()); 
			//JRF next steps: do above for each field we want to manage on the page, then...
			/// update the edit.jsp by removing all thsoe userProfileAsIs junky things
			// then we can whack model.addAtt-userprofile stuff
			
			model.addAttribute("userProfileAsis", userProfileObj); 
			
			
			
//			// records in stateterritory dropdown
//			List<StateterritoryMdl> stateterritoryList = stateterritorySrv.returnAll();
//			model.addAttribute("stateterritoryList", stateterritoryList);  
			
			// log page being rendered
			System.out.println("Page Display: ProfileEdit");
			
							// START: fun with making lists for page consumption
							
							// quick/dirty stateList
							String[] stateList = { "Alabama", "Alaska", "Arizona"};
							model.addAttribute("stateList", stateList ); 
							
							// initalize empty array list (that we'll populate then send to page)
							ArrayList<Object> stateListEnhanced = new ArrayList<Object>();
							
							// initialize feeder lists: 
							String[] stateSpellList = {"Alabama" , "Alaska" , "Arizona"}; 
							String[] stateAbbrvList = {"AL" , "AK" , "AZ"}; 
							
							// get variable for submitting to the loop as upper boundary
							Integer stateListSpellDrawerCount =  stateSpellList.length; 
							 
							// initialize for-loop 
							
							for (int i=0; i < stateListSpellDrawerCount; i++  ) {
				//				System.out.println("value of item in drawer " + i + " -- " + stateSpellList[i]);
								HashMap<String, String> singleStateHashMap = new HashMap<String, String>();
								singleStateHashMap.put("'StateName'", "'" + stateSpellList[i] + "'");
								singleStateHashMap.put("'StateAbbv'", "'" + stateAbbrvList[i] + "'");
								stateListEnhanced.add(singleStateHashMap); 
								System.out.println("stateListEnhanced: " + stateListEnhanced); 
							}
							
							model.addAttribute("stateListEnhanced", stateListEnhanced ); 
							
							// END: fun with making lists for page consumption
			
			return "profile/edit.jsp";
		}
		
		// process the edit
		@PostMapping("/profile/edit")
		public String PostTheEditProfile(
				@Valid 
				@ModelAttribute("userProfileTobe") UserupdateMdl userupdateMdl
				, BindingResult result
				, Model model
				, HttpSession session
				, RedirectAttributes redirectAttributes
				) {
			
			// log out the unauth / deliver the auth use data
			if(session.getAttribute("userId") == null) {return "redirect:/logout";}
			Long authenticatedUserId = (Long) session.getAttribute("userId");
			System.out.println("authenticatedUserId: " + authenticatedUserId); 
//			model.addAttribute("authUser", userSrv.findById(authenticatedUserId));
			
			UserMdl currentUserMdl = userSrv.findById(authenticatedUserId); //  gets the userModel object by calling the user service with the session user id
			currentUserMdl.setEmail(userupdateMdl.getEmail()); 
			currentUserMdl.setUserName(userupdateMdl.getUserName()); 
			currentUserMdl.setFirstName(userupdateMdl.getFirstName() ); 
			currentUserMdl.setLastName(userupdateMdl.getLastName() ); 
			
			currentUserMdl.setAboutMe(userupdateMdl.getAboutMe() ); 
			currentUserMdl.setCity(userupdateMdl.getCity() ); 
//			currentUserMdl.setStateTerritory(userupdateMdl.getStateTerritory() ); 
			currentUserMdl.setZipCode(userupdateMdl.getZipCode() ); 
			
//			currentUserMdl.setStateterritoryMdl(userupdateMdl.getStateterritoryMdl()); 
			
//				currentUserMdl.setConfirm("hello");  // this line not needed when validation taken off the confirm password field on the userMdl.  
			
			userSrv.updateUserProfile(currentUserMdl, result);
			
			if (result.hasErrors() ) { 
				System.out.println("on profile/edit error path"); 
				return "profile/edit.jsp";
			} else {
				return "redirect:/profile/" + currentUserMdl.getId(); 
			}
		}
		
		
		
// end of ctl methods
}
