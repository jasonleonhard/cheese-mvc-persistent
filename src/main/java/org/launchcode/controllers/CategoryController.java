package org.launchcode.controllers;
import org.launchcode.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.launchcode.models.data.CategoryDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.Valid;

// The index handler should correspond to the route "" (that is, the path /category),
// and it should retrieve the list of all categories. This is done via the
// categoryDao object: categoryDao.findAll() returns a collection (actually, an Iterable)
// of all Category objects managed by categoryDao. Use this snippet to retrieve the list
// of categories, and then pass the list into the view by adding it to model. Also add a
// "title" to the model ("Categories" works).

@Controller
@RequestMapping("category") // /category
public class CategoryController {

    // This creates a private field categoryDao of type CategoryDao. This object will be the mechanism with which we interact with objects stored in the database. Recall that Spring will do the work of creating a class that implements CategoryDao and putting one of those objects in the categoryDao field when the application starts up. And all of this is thanks to the @Autowired annotation.
    // This code would need to be added to each controller class that you want to have access to the persistent collections defined within categoryDao.
    @Autowired
    CategoryDao categoryDao; // object
//    private CategoryDao categoryDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, @RequestParam(defaultValue = "0") int id) {
        model.addAttribute("title", "Categories");
        model.addAttribute("categories", categoryDao.findAll());
        return "category/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new Category());
        model.addAttribute("title", "Add Category");
        return "category/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Category category, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Category");
            return "category/add";
        }
        categoryDao.save(category);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCategoryForm(Model model) {
        model.addAttribute("category", categoryDao.findAll());
        model.addAttribute("title", "Remove Category");
        return "category/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCategoryForm(@RequestParam int[] ids) {
        for (int id : ids) {
            categoryDao.delete(id);
        }
        return "redirect:";
    }
}
