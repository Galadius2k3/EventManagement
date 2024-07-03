package com.hutech.demo.controller;

import com.hutech.demo.model.Type;
import com.hutech.demo.service.TypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TypeController {

    @Autowired
    private final TypeService typeService;

    @GetMapping("/types/add")
    public String showAddForm(Model model) {
        model.addAttribute("type", new Type());
        return "/types/add-type";
    }

    @PostMapping("/types/add")
    public String addType(@Valid Type type, BindingResult result) {
        if (result.hasErrors()) {
            return "/types/add-type";
        }
        typeService.addType(type);
        return "redirect:/types";
    }

    @GetMapping("/types")
    public String listTypes(Model model) {
        List<Type> types = typeService.getAllTypes();
        model.addAttribute("types", types);
        return "/types/types-list";
    }

    @GetMapping("/types/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Type type = typeService.getTypeById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid type Id:" + id));
        model.addAttribute("type", type);
        return "/types/update-type";
    }

    @PostMapping("/types/update/{id}")
    public String updateType(@PathVariable("id") Long id, @Valid Type type, BindingResult result, Model model) {
        if (result.hasErrors()) {
            type.setId(id);
            return "/types/update-type";
        }

        typeService.updateType(type);
        model.addAttribute("types", typeService.getAllTypes());
        return "redirect:/types";
    }

    @GetMapping("/types/delete/{id}")
    public String deleteType(@PathVariable("id") Long id, Model model) {
        Type type = typeService.getTypeById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid type Id:" + id));

        typeService.deleteTypeById(id);
        model.addAttribute("types", typeService.getAllTypes());
        return "redirect:/types";
    }
}
