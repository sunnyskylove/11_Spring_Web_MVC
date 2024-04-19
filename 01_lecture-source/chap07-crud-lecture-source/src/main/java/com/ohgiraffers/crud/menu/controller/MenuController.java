package com.ohgiraffers.crud.menu.controller;

import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import com.ohgiraffers.crud.menu.model.service.MenuService;
import jdk.jfr.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/menu")    // menu 에 대한 하위개체들은 내가 처리하겠다!!
public class MenuController {



    private static final Logger logger = LogManager.getLogger(MenuController.class);

    /* 의존성 주입받기!!*/
    private final MenuService menuService;
    private final MessageSource messageSource;        // 메세지를 읽을 수 있는 객체


    /* 생성자 만들기~*/
    @Autowired
    public MenuController(MenuService menuService, MessageSource messageSource) {
        this.menuService = menuService;
        this.messageSource = messageSource;
    }

    @GetMapping("/list")       // ~~ 내가 처리하겠다~~
    public String findMenuList(Model model) {

        List<MenuDTO> menuList = menuService.findAllMenus();

        model.addAttribute("menuList", menuList);   // 담을 공간 model 위에 만들어놔서, 뷰에 쏠 것만~~

        return "menu/list";

    }

    @GetMapping("regist")
    public void registPage() {}

    @GetMapping(value = "category", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<CategoryDTO> findCategoryList() {
        return menuService.findAllCategory();
    }

    @PostMapping("regist")
    public String registMenu(MenuDTO newMenu, RedirectAttributes rttr, Locale locale) {

        // return 은 없음. DB에 넣으면 되서~~

        menuService.registNewMenu(newMenu);
        /* 참고. locale : 지역(나라) 에 대한 정보 다국어 처리와 관련 된 정보 */
        logger.info("Locale : {}", locale);
//        rttr.addFlashAttribute("successMessage","신규 메뉴 등록에 성공하셨습니다!!!");  // 얘는 썼었는데 지움~
        rttr.addFlashAttribute("successMessage", messageSource.getMessage("registMenu",new Object[]{newMenu.getName()},locale));

        // ▼ forward 방식이 아닌 redirect 방식으로 리턴해주기
        return "redirect:/menu/list";

    }

}