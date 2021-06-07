package com.softper.userservice.services;

import com.softper.userservice.models.Person;
import com.softper.userservice.resources.comunications.UserBoundResponse;

public interface IPersonService extends ICrudService<Person> {
    UserBoundResponse findPeopleById(int id);
    UserBoundResponse findAllPersons();
    Person getPersonById(int personId);
    UserBoundResponse getPersonModelById(int personId);
}
