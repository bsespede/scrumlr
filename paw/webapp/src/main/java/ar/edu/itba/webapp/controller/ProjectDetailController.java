package ar.edu.itba.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.interfaces.ProjectService;

@Controller
@RequestMapping("/project")
public class ProjectDetailController {
	
	@Autowired
	ProjectService ps;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView getResource(@PathVariable int id) {
		final ModelAndView mav = new ModelAndView("project/iterationList");
		mav.addObject("project", ps.getProjectWithDetailsById(id));
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView saveResource() {
		final ModelAndView mav = new ModelAndView("helloworld");
		return mav;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ModelAndView modifyResource(@PathVariable String id) {
		final ModelAndView mav = new ModelAndView("helloworld");
		return mav;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ModelAndView deleteResource(@PathVariable String id) {
		final ModelAndView mav = new ModelAndView("helloworld");
		return mav;
	}

}