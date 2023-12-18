package com.violetbeach.common.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 각 서비스에 특정 membershipId로 Validation
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubTask {
    private String membershipID;
    private String subTaskName;
    private String taskType; // "banking", "membership"
    private String status; // "success", "fail", "ready"
}