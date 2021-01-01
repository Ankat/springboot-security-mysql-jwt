package com.ankat.controller;

import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "resourceController")
@RequestMapping(value = "/api")
public class ResourceController {

    @GetMapping(value = "/", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public String defaultWelcomePage() {
        return "<h1>Hello World</h1>";
    }

    @GetMapping(value = "/user", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public String defaultUserPage() {
        return "<h1>Hello World User</h1>";
    }

    @GetMapping(value = "/admin", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public String defaultAdminPage() {
        return "<h1>Hello World Admin</h1>";
    }
}
