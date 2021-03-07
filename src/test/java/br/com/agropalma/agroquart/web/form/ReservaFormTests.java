package br.com.agropalma.agroquart.web.form;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * <h1>ReservaFormTests.java</h1>
 * ---
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 07/03/2021
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ReservaFormTests {

    @Test
    public void testDiferencaNegativaEntreDatas() {
        ReservaForm reservaForm = new ReservaForm();
        reservaForm.setDataInicio(LocalDate.of(2021, 2, 12));
        reservaForm.setHoraInicio(LocalTime.of(12, 45, 0));

        reservaForm.setDataTermino(LocalDate.of(2020, 1, 13));
        reservaForm.setHoraTermino(LocalTime.of(11, 30, 0));


        Assert.assertFalse(reservaForm.isValidDates());
    }

    @Test
    public void testDiferencaEntreDatas() {
        ReservaForm reservaForm = new ReservaForm();
        reservaForm.setDataInicio(LocalDate.of(2021, 2, 12));
        reservaForm.setHoraInicio(LocalTime.of(12, 45, 0));

        reservaForm.setDataTermino(LocalDate.of(2021, 2, 13));
        reservaForm.setHoraTermino(LocalTime.of(11, 30, 0));


        Assert.assertTrue(reservaForm.isValidDates());
    }
}
