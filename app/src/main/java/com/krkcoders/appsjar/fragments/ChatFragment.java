package com.krkcoders.appsjar.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.krkcoders.appsjar.R;
import com.krkcoders.appsjar.adapters.ChatAdapter;
import com.krkcoders.appsjar.models.ChatMessage;
import com.krkcoders.appsjar.service.PostNotificationService;

import java.util.ArrayList;

public class ChatFragment extends Fragment {

    private EditText messageET;
    private ListView messagesContainer;
    private ImageButton sendBtn;
    private ChatAdapter adapter;
    private ArrayList<ChatMessage> chatHistory;

    public ChatFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.chat_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initControls();
    }

    //todo set current username to message
    private void initControls() {
        messagesContainer = (ListView) getView().findViewById(R.id.messagesContainer);
        messageET = (EditText) getView().findViewById(R.id.messageEdit);
        sendBtn = (ImageButton) getView().findViewById(R.id.chatSendButton);
        loadDummyHistory();

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = messageET.getText().toString();
                if (TextUtils.isEmpty(messageText)) {
                    return;
                }

                ChatMessage chatMessage = new ChatMessage();
                chatMessage.setId(213);
                chatMessage.setMessage(messageText);
                chatMessage.setUserName("Karol");
                chatMessage.setMe(true);

                messageET.setText("");
                displayMessage(chatMessage);
                sendMessage(chatMessage);
            }
        });
    }

    private void loadDummyHistory() {
        //todo download history from api or local database

        chatHistory = new ArrayList<ChatMessage>();

        ChatMessage msg = new ChatMessage();
        msg.setId(1);
        msg.setMe(false);
        msg.setMessage("Hi");
        msg.setUserName("testuser1");
        chatHistory.add(msg);

        adapter = new ChatAdapter(getActivity(), new ArrayList<ChatMessage>());
        messagesContainer.setAdapter(adapter);

        for (int i = 0; i < chatHistory.size(); i++) {
            ChatMessage message = chatHistory.get(i);
            displayMessage(message);
        }
    }

    public void displayMessage(ChatMessage message) {
        adapter.add(message);
        adapter.notifyDataSetChanged();
        scroll();
    }

    private void scroll() {
        messagesContainer.setSelection(messagesContainer.getCount() - 1);
    }

    @Override
    public void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(getContext()).registerReceiver((mMessageReceiver),
                new IntentFilter("Message")
        );
    }

    @Override
    public void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(mMessageReceiver);
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {


        @Override
        public void onReceive(Context context, Intent intent) {


            ChatMessage message = new ChatMessage();
            message.setMe(false);
            message.setUserName(intent.getExtras().getString("Title"));
            message.setMessage(intent.getExtras().getString("Body"));

            if(!messageIsMy(message)){
                adapter.add(message);
                adapter.notifyDataSetChanged();
                scroll();

            }
        }
    };

    private void sendMessage(ChatMessage chatMessage) {
        new PostNotificationService(chatMessage.getUserName(),chatMessage.getMessage()).execute();
    }

    //todo check that message is our
    private boolean messageIsMy(ChatMessage message){
        return false;
    }

}
