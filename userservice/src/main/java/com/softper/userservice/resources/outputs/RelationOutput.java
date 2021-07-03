package com.softper.userservice.resources.outputs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RelationOutput {
    private int id;
    private String userFrom;
    private String userTo;
}
