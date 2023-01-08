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
		
		if(result.hasErrors()) {
			return "createaccount";
		}
		
		user.setAuthority("ROLE_USER");
		user.setEnabled(true);

		userService.create(user);
		
		return "home";

	}
}