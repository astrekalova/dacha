package de.unipotsdam.dacha.dao;

import org.springframework.stereotype.Repository;


@Repository("userDao")
public class UserDaoImpl extends HibernateDao<User> implements UserDao {

}
