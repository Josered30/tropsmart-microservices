package com.softper.configservice.resources.outputs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RelationOutput {
    private String userFrom;
    private String userTo;
}
