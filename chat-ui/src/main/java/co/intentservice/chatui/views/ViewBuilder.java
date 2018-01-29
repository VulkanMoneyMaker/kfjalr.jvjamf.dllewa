package co.intentservice.chatui.views;

import android.content.Context;
import android.view.View;

public class ViewBuilder implements ViewBuilderInterface {

    /**
     * Returns a MessageView object which is used to display messages that the chat-ui
     * has received.
     * @param context A context that is used to instantiate the view.
     * @return        MessageView object for displaying received messages.
     */
    public MessageView buildRecvView(Context context) {

        MessageView view = new ItemRecvView(context);
        return view;

    }

    /**
     * Returns a MessageView object which is used to display messages that the chat-ui
     * has sent.
     * @param context A context that is used to instantiate the view.
     * @return        MessageView object for displaying sent messages.
     */
    public MessageView buildSentView(Context context) {

        MessageView view = new ItemSentView(context);
        return view;

    }

}
