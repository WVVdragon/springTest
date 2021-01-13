package com.lin.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LYN
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class History {
    private int historyId;
    private String historyText;
    private int historyTaskId;
    private String historyNote;

    public History(String historyText, int historyTaskId, String historyNote) {
        this.historyText = historyText;
        this.historyTaskId = historyTaskId;
        this.historyNote = historyNote;
    }
}
