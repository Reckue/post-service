package com.reckue.note.models.entities;

import com.reckue.note.models.transfers.NoteDescription;
import com.reckue.note.utils.Described;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Note implements Described<NoteDescription> {

    @Id
    private String id;
    private String payload;

    @Override
    public NoteDescription toDescription() {
        String description = this.getPayload().substring(0, 20).concat("...");
        return NoteDescription.builder()
                .id(this.getId())
                .description(description)
                .build();
    }
}
