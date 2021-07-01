package com.softper.userservice.services;

import com.softper.userservice.models.Person;

import com.tropsmart.resources.comunications.UserBoundResponse;

public interface IPersonService extends ICrudService<Person> {
    UserBoundResponse findPeopleById(int id);
    UserBoundResponse findAllPersons();
    Person getPersonById(int personId);
}
