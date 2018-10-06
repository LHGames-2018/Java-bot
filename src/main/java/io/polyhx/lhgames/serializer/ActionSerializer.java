////////////////////////////////////////////
//         DO NOT TOUCH THIS FILE         //
////////////////////////////////////////////

package io.polyhx.lhgames.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.polyhx.lhgames.game.action.IAction;

import java.lang.reflect.Type;

/**
 * This class serialises an action to JSON.
 */
public class ActionSerializer implements JsonSerializer<IAction> {
    @Override
    public JsonElement serialize(IAction action, Type type, JsonSerializationContext context) {
        JsonObject jsonMerchant = new JsonObject();
        jsonMerchant.addProperty("ActionName", action.getActionType().getJSON());
        jsonMerchant.addProperty("Content", action.getJSONContent());
        return jsonMerchant;
    }
}
