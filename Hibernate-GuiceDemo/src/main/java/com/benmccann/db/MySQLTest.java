package com.benmccann.db;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class MySQLTest {

    @Test
    public void testDb() throws SQLException {
        final Injector injector = Guice.createInjector(new DbModule());
        final ExamplePersistedClassDao examplePersistedClassDao = injector.getInstance(ExamplePersistedClassDao.class);

        final ExamplePersistedClass example = new ExamplePersistedClass();
        example.setOtherField("hello world");
        examplePersistedClassDao.saveInNewTransaction(example);

        final ExamplePersistedClass retrieved = examplePersistedClassDao.getByOtherField("hello world");

        Assert.assertEquals(example.getId(), retrieved.getId());
        Assert.assertEquals(example.getOtherField(), retrieved.getOtherField());
    }

}