package com.softper.driverservice.resources.comunications;

import java.util.List;

import com.softper.driverservice.resources.outputs.AuthenticatedOutput;
import com.softper.driverservice.resources.outputs.BalanceOutput;
import com.softper.driverservice.resources.outputs.BlockedOutput;
import com.softper.driverservice.resources.outputs.FavoriteOutput;
import com.softper.driverservice.resources.outputs.PersonOutput;
import com.softper.driverservice.resources.outputs.PlanOutput;
import com.softper.driverservice.resources.outputs.SubscriptionOutput;
import com.softper.driverservice.resources.outputs.UserOutput;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserBoundResponse extends BaseResponse {
    public UserBoundResponse(String title, String message, Integer status) { super(title, message, status);}

    AuthenticatedOutput authenticatedOutput;

    List<AuthenticatedOutput> authenticatedOutputList;

    BlockedOutput blockedOutput;

    List<BlockedOutput> blockedOutputs;

    FavoriteOutput favoriteOutput;

    List<FavoriteOutput> favoriteOutputs;

    PersonOutput personOutput;

    List<PersonOutput> personOutputs;

    PlanOutput planOutput;

    List<PlanOutput> planOutputs;

    SubscriptionOutput subscriptionOutput;

    List<SubscriptionOutput> subscriptionOutputs;

    UserOutput userOutput;

    List<UserOutput> userOutputs;
}
