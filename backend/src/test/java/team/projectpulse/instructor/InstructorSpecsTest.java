package team.projectpulse.instructor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.Locale;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.domain.Specification;

class InstructorSpecsTest {

    private Locale originalLocale;

    @BeforeEach
    void setTurkishLocale() {
        originalLocale = Locale.getDefault();
        Locale.setDefault(Locale.forLanguageTag("tr-TR"));
    }

    @AfterEach
    void restoreLocale() {
        Locale.setDefault(originalLocale);
    }

    @Test
    @SuppressWarnings("unchecked")
    void hasEmail_lowercasesInputIndependentOfDefaultLocale() {
        Root<Instructor> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder cb = mock(CriteriaBuilder.class);

        Specification<Instructor> spec = InstructorSpecs.hasEmail("INSTRUCTOR@TCU.EDU");
        spec.toPredicate(root, query, cb);

        // Under tr-TR, the buggy code produces "ınstructor@tcu.edu" (dotless ı)
        verify(cb).equal(any(), eq("instructor@tcu.edu"));
    }
}