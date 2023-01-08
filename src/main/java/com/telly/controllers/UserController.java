package com.telly.controllers;

import java.security.Principal;
import java.util.List;

public class UserController{
    public String showLogin() {
		return "login";
	}
	public String showLogout() {
		return "loggedout";
	}
	public String createAccount(Model model, Principal principal) {

		model.addAttribute("user", new User());

		return "createaccount";
	}
	public String createUser(@Validated(FormValidationGroup.class) User user, BindingResult result) {

		if (result.hasErrors()) {
			return "createaccount";
		}

		user.setAuthority("ROLE_USER");
		user.setEnabled(true);

		userService.create(user);

		@Autowired
		UserService userService;

		@Autowired
		ReserveService reserveService;


		@RequestMapping(value = "/reservebook", method = RequestMethod.POST)
		public String createReserveBook (@Validated(FormValidationGroup.class) Reserve reserve, BindingResult
		result, Principal principal){

			if (result.hasErrors()) {
				return "reservebus";
			}

			String username = principal.getName();
			reserve.getUser().setUsername(username);

			reserveService.reserve(reserve);


			return "home";

		}
		@RequestMapping(value = "/getreservations", method = RequestMethod.GET)
		public String getReserveBook (@Validated(FormValidationGroup.class) Reserve reserve, Model model, Principal
		principal){


			String username = principal.getName();
			reserve.getUser().setUsername(username);

			List<Reserve> reserves = reserveService.getReserves(username);
			model.addAttribute("reserves", reserves);
			System.out.println(reserves);


			return "home";

		}
		}
	}

