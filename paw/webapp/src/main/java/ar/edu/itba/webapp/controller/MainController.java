package ar.edu.itba.webapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.interfaces.TaskService;
import ar.edu.itba.models.Project;
import ar.edu.itba.models.Story;
import ar.edu.itba.models.Task;
import ar.edu.itba.models.User;

@Controller
@RequestMapping("/")
public class MainController extends BaseController {
		
	@Autowired
	private TaskService ts;
	
	@RequestMapping(method = RequestMethod.GET, name = "dashboard")
	public ModelAndView getResource() {
		final ModelAndView mav = new ModelAndView("main/dashboard");
		final User user = super.user();
		final Map<Project, Map<Story, List<Task>>> pendingTasks = ts.getPendingTasks(user);
		mav.addObject("pendingTasks", pendingTasks);
		return mav;
	}
	
	
}