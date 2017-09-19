package org.launchcode.controllers;
import org.launchcode.models.Category;
import org.launchcode.models.Cheese;
//import org.launchcode.models.CheeseType;
import org.launchcode.models.data.CategoryDao;
import org.launchcode.models.data.CheeseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("cheese")
public class CheeseController {
    // annotation that spring uses to create table. part of Spring's dependency injection framework,
    // works with Spring's CrudRepository interface, along with the @Repository annotations,
    // and some other implicit Spring Boot settings.
    // automtically populates cheeseDao field
    @Autowired
    CheeseDao cheeseDao;

    @Autowired
    CategoryDao categoryDao;

    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "My Cheeses");
        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
//      model.addAttribute("cheeseTypes", CheeseType.values());  // old way
        model.addAttribute("categories", categoryDao.findAll()); // new way
        model.addAttribute(new Cheese());
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute  @Valid Cheese newCheese,
                                       Errors errors, @RequestParam int categoryId, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Cheese");
            model.addAttribute("categories", categoryDao.findAll());   // new way
            return "cheese/add";
        }
        Category cat = categoryDao.findOne(categoryId);
        newCheese.setCategory(cat);
        cheeseDao.save(newCheese);
        return "redirect:";
    }

    @ResponseBody
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public HashMap missingParamterHandler(Exception exception) {
        /**inspect and exception and obtain meaningful message*/
        // return "default-error-view"; /*view name of your error jsp*/
        return new HashMap() {
            {
                put("result", "failed");
                put("type", "category_must_exist");
            }
        };
    }

//    alternative implementation
//    @ExceptionHandler(MissingServletRequestParameterException.class)
//    public void handleMissingParams(MissingServletRequestParameterException ex) {
////    public String handleMissingParams(MissingServletRequestParameterException ex) {
//        String name = ex.getParameterName();
//        System.out.println(name + " parameter is missing");
//        // Actual exception handling
////        return "category/add";
//    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "Remove Cheese");
        return "cheese/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] ids) {
        for (int id : ids) {
            cheeseDao.delete(id);
        }
        return "redirect:";
    }

    // /cheese/category?id=12 works
    @RequestMapping(value = "category", method = RequestMethod.GET)
    public String category(Model model, @RequestParam int id) {
        Category cat = categoryDao.findOne(id);
        List<Cheese> cheeses = cat.getCheeses();
        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "Cheeses in Category: " + cat.getName());
        return "cheese/index";
    }

    // /cheese/category/13
    @RequestMapping(value = "category/{id}", method = RequestMethod.GET)
    public String cheeseCategoryById(Model model, @PathVariable int id) {
        Category cat = categoryDao.findOne(id);
        List<Cheese> cheeses = cat.getCheeses();
        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "Cheeses in Category: " + cat.getName());
        return "cheese/index";
    }

    // /cheese/13
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String cheeseShowById(Model model, @PathVariable int id) {
        Category cat = categoryDao.findOne(id);
        List<Cheese> cheeses = cat.getCheeses();
        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "Cheeses in Category: " + cat.getName());
        return "cheese/index";
    }



}

