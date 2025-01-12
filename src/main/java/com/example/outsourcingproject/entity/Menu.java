package com.example.outsourcingproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "menus")
@Getter
public class Menu extends BaseEntity {

    @Comment("메뉴 식별자")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long id;

    @Comment("메뉴 이름")
    @Column(
        name = "menu_name",
        nullable = false,
        length = 100
    )
    private String menuName;

    @Comment("메뉴 가격")
    @Column(
        name = "menu_price",
        nullable = false
    )
    private Integer menuPrice;

    @Comment("메뉴 정보")
    @Column(
        name = "menu_info",
        nullable = false
    )
    private String menuInfo;

    @Comment("가게 식별자")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "store_id",
        nullable = false
    )
    private Store store;

    @Comment("첫 번째 메뉴 카테고리 식별자")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_category_one_id")
    private MenuCategory menuCategoryOne;

    @Comment("두 번째 메뉴 카테고리 식별자")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_category_two_id")
    private MenuCategory menuCategoryTwo;

    @Comment("세 번째 메뉴 카테고리 식별자")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_category_three_id")
    private MenuCategory menuCategoryThree;

    protected Menu() {
    }

    public Menu(
        String menuName,
        Integer menuPrice,
        String menuInfo,
        Store store,
        MenuCategory menuCategoryOne,
        MenuCategory menuCategoryTwo,
        MenuCategory menuCategoryThree
    ) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuInfo = menuInfo;
        this.store = store;
        this.menuCategoryOne = menuCategoryOne;
        this.menuCategoryTwo = menuCategoryTwo;
        this.menuCategoryThree = menuCategoryThree;
    }

    public void update(
        String menuName,
        Integer menuPrice,
        String menuInfo
    ) {
        if (menuName != null) {
            this.menuName = menuName;
        }
        if (menuPrice != null) {
            this.menuPrice = menuPrice;
        }
        if (menuInfo != null) {
            this.menuInfo = menuInfo;
        }
    }
}