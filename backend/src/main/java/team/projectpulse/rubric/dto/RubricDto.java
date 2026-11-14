package team.projectpulse.rubric.dto;

import java.util.Set;
import java.util.Collections;
import java.util.LinkedHashSet;

public record RubricDto(
        Integer rubricId,
        String rubricName,
        Set<CriterionDto> criteria,
        Integer courseId) {

    public RubricDto {
        criteria = criteria == null ? null : Collections.unmodifiableSet(new LinkedHashSet<>(criteria));
    }
}
