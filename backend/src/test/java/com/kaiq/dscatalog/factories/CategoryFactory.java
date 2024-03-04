package com.kaiq.dscatalog.factories;

import com.kaiq.dscatalog.entities.Category;

public class CategoryFactory {

    public static Category createCategory() {
        return new Category(1L, "Electronics");
    }

}
