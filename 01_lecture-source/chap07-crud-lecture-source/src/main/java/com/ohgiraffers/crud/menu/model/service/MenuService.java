package com.ohgiraffers.crud.menu.model.service;

import com.ohgiraffers.crud.menu.model.dto.MenuAndCategoryDTO;
import com.ohgiraffers.crud.menu.model.dao.MenuMapper;
import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service      // 난 서비스 계층이야~ bean 등록!!
public class MenuService {

    private final MenuMapper menuMapper;

    @Autowired
    public MenuService(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    // 메뉴로~~
    public List<MenuDTO> findAllMenus() {

        return menuMapper.findAllMenus();
    }

    // 카테고리로~~
    public List<CategoryDTO> findAllCategory() {

        return menuMapper.findAllCategory();
    }

    /* 필기.
     *   @Transactional 어노테이션은 스프링 프레임워크에서 제공하는 트랜잭션 관리 지원하는 어노테이션 이다.
     *   트렌젝션은 데이터베이스의 상태를 변화시키는 일(작업)을 하나의 단위로 묶는 작업을 의미한다.
     *   데이터 조작에 관련 된 작업이 일어날 때(c, u, d) 메소드 실행이 완료되면 commit,
     *   예외가 발생하게 되면 rollback
     *  */
    @Transactional
    public void registNewMenu(MenuDTO newMenu) {
        menuMapper.registNewMenu(newMenu);
    }

    public List<MenuAndCategoryDTO> findAllMenuAndCategory() {

        return menuMapper.findAllMenuAndCategoryList();


    }

    @Transactional
    public void deleteMenuByCode(int code) {

        menuMapper.deleteMenuByCode(code);

    }
}