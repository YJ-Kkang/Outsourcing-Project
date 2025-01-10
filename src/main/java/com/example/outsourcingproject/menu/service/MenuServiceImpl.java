package com.example.outsourcingproject.menu.service;

import com.example.outsourcingproject.entity.Menu;
import com.example.outsourcingproject.entity.Store;
import com.example.outsourcingproject.menu.dto.request.CreateMenuRequestDto;
import com.example.outsourcingproject.menu.dto.request.UpdateMenuRequestDto;
import com.example.outsourcingproject.menu.dto.response.CreateMenuResponseDto;
import com.example.outsourcingproject.menu.dto.response.UpdateMenuResponseDto;
import com.example.outsourcingproject.menu.repository.MenuRepository;
import com.example.outsourcingproject.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;

    @Transactional
    @Override
    public CreateMenuResponseDto createMenu(
        Long storeId,
        CreateMenuRequestDto requestDto
    ) {
        Store foundStore = storeRepository.findById(storeId)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
            ); // todo 가게 식별자로 가게를 조회했을 때 가게가 없으면 예외 처리

        Menu menuToSave = new Menu(
            requestDto.getMenuName(),
            requestDto.getMenuPrice(),
            requestDto.getMenuInfo(),
            foundStore
        );

        Menu savedMenu = menuRepository.save(menuToSave);

        return new CreateMenuResponseDto(
            savedMenu.getId(),
            savedMenu.getMenuName(),
            savedMenu.getMenuPrice(),
            savedMenu.getMenuInfo()
        );
    }

    @Transactional
    @Override
    public UpdateMenuResponseDto updateMenu(
        Long storeId,
        Long menuId,
        UpdateMenuRequestDto requestDto
    ) {
        Store foundStore = storeRepository.findById(storeId)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
            ); // todo 가게 식별자로 가게를 조회했을 때 가게가 없으면 예외 처리

        Menu foundMenu = menuRepository.findById(menuId)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
            ); // todo 메뉴 식별자로 메뉴를 조회했을 때 메뉴가 없으면 예외 처리

        boolean isMenuFromDifferentStore = !foundMenu.getStore().getId().equals(foundStore.getId());

        if (isMenuFromDifferentStore) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } // todo 수정하려는 메뉴가 속한 가게의 id와 사장님이 입력한 storeId가 다르면 예외 처리

        foundMenu.update(
            requestDto.getMenuName(),
            requestDto.getMenuPrice(),
            requestDto.getMenuInfo()
        );

        return new UpdateMenuResponseDto(
            foundMenu.getMenuName(),
            foundMenu.getMenuPrice(),
            foundMenu.getMenuInfo()
        );
    }

    @Transactional
    @Override
    public void deleteMenu(Long menuId) {
        Menu foundMenu = menuRepository.findById(menuId)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
            );

        foundMenu.markAsDeleted();
    }
}
