package com.rao.EasySchool.service;

import com.rao.EasySchool.constants.GlobalConstant;
import com.rao.EasySchool.model.Person;
import com.rao.EasySchool.model.Roles;
import com.rao.EasySchool.repository.PersonRepository;
import com.rao.EasySchool.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RolesRepository rolesRepository;

    public boolean createNewPerson(Person person){
        boolean isSaved = false;
        Roles role = rolesRepository.getByRoleName(GlobalConstant.STUDENT_ROLE);
        person.setRoles(role);
        person = personRepository.save(person);
        if (person.getPersonId() > 0) {
            isSaved = true;
        }
        return isSaved;
    }
}
