package com.delmon.deliverymonitoring.department;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DepartmentControllerTest {
    @Mock
    private DepartmentService service;
    private DepartmentController controller;

    @BeforeEach
    void setUp() {
        controller = new DepartmentController(service);
    }

    @Test
    void canSaveDepartment() {
        // given
        Department department = new Department("address");

        // when
        controller.saveDepartment(department);

        // then
        verify(service).saveNewDepartment(department);
    }

    @Test
    void canFindDepartment() {
        // given
        String address = "address";

        // when
        controller.findDepartment(address);

        // then
        verify(service).findDepartmentByAddress(address);
    }

    @Test
    void canFindAll() {
        // when
        controller.findAll();

        // then
        verify(service).findAllDepartments();
    }

    @Test
    void canDeleteDepartment() {
        // given
        String address = "address";

        // when
        controller.deleteDepartment(address);

        // then
        verify(service).deleteDepartmentByAddress(address);
    }
}
