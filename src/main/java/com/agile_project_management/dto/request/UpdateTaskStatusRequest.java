package com.agile_project_management.dto.request;

import com.agile_project_management.model.enums.TaskStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateTaskStatusRequest {
    @NotNull(message = "Le nouveau statut est obligatoire")
    private TaskStatus status;
}