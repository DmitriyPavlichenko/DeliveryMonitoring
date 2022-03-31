package com.delmon.deliverymonitoring.department;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {
    @Mock
    private DepartmentRepository mockedRepository;
    private DepartmentService service;

    @BeforeEach
    void setUp() {
        service = new DepartmentService(mockedRepository);
    }

    @Test
    void canSaveNewDepartment() {
        // given
        Department givenDepartment = new Department("address");
        given(mockedRepository.existsByAddress(anyString())).willReturn(false);

        // when
        service.saveNewDepartment(givenDepartment);

        // then
        verify(mockedRepository).save(givenDepartment);
    }

    @Test
    void cannotSaveNewDepartment() {
        // given
        Department givenDepartment = new Department("address");
        given(mockedRepository.existsByAddress(anyString())).willReturn(true);

        // when
        Throwable throwable = catchThrowable(() -> service.saveNewDepartment(givenDepartment));

        // then
        assertThat(throwable)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("address is already existing");
    }

    @Test
    void canFindDepartmentByAddress() {
        // given
        String address = "address";
        Department givenDepartment = new Department("address");
        given(mockedRepository.findByAddress(anyString())).willReturn(java.util.Optional.of(givenDepartment));

        // when
        service.findDepartmentByAddress(address);

        // then
        verify(mockedRepository).findByAddress(address);
    }

    @Test
    void canFindAllDepartments() {
        // when
        service.findAllDepartments();

        // then
        verify(mockedRepository).findAll();
    }

    @Test
    void canDeleteDepartmentByAddress() {
        // given
        String address = "address";
        given(mockedRepository.existsByAddress(anyString())).willReturn(true);

        // when
        service.deleteDepartmentByAddress(address);

        // then
        verify(mockedRepository).deleteByAddress(address);
    }

    @Test
    void cannotDeleteDepartmentByAddress() {
        // given
        String address = "address";
        given(mockedRepository.existsByAddress(anyString())).willReturn(false);

        // when
        Throwable throwable = catchThrowable(() -> service.deleteDepartmentByAddress(address));

        // then
        assertThat(throwable)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("address address isn't existing");
    }
}
