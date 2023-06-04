package com.xxxweb.tasktracker.service;

import com.xxxweb.tasktracker.web.rest.errors.BadRequestAlertException;

public class NotAuthorityException extends BadRequestAlertException {

    public NotAuthorityException() {
        super("Do not have right access authority.", "Project", "noauthority");
    }
}
