package com.rao.EasySchool.controllers;

import com.rao.EasySchool.model.Contact;
import com.rao.EasySchool.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;

@Controller
public class ContactController {
    Logger log = Logger.getLogger(ContactController.class.getName());

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping("/contact")
    public String displayContactPage() {
        return "contact.html";
    }

    @RequestMapping(value = "/saveMsg", method = RequestMethod.POST)
    public ModelAndView saveMessage(Contact contact) {
        log.info("name: " + contact.getName());
        log.info("mobileNum: " + contact.getMobileNum());
        log.info("subject: " + contact.getSubject());
        log.info("email: " + contact.getEmail());
        log.info("message: " + contact.getMessage());
        contactService.saveMessageDetails(contact);
        return new ModelAndView("redirect:/contact");
    }
}
