package capgemini.service;

import capgemini.dto.HistoryTo;

public interface HistoryService {

    HistoryTo findHistoryEntryById(Long id);

    HistoryTo addNewHistoryEntry(HistoryTo historyTo);

    void deleteHistoryEntry(HistoryTo historyTo);
}
