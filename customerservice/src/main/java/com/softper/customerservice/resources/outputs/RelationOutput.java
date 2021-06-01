package com.softper.customerservice.resources.outputs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RelationOutput {
    private String userFrom;
    private String userTo;
}
