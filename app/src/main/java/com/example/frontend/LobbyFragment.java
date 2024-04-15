
package com.example.frontend;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;


public class LobbyFragment extends Fragment {

    private Button startGame;
    ImageButton retCreateLobby;
    private Button rulesBtn;
    private RecyclerView recyclerView;
    ArrayList<Player> userList;
    PlayerAdapter adapter;

    private TextView playerCount;
    public LobbyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lobby_, container, false);
        findViews(view);
        onClickStart();
        getDummyDataForRecylerView();


        adapter = new PlayerAdapter(userList);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        int allPlayer = adapter.getItemCount();

       playerCount.setText(allPlayer + "");

        onClickStart();
        onReturnBtnClick();

        return view;
    }

    private void getDummyDataForRecylerView() {
        userList = new ArrayList<>();
        Player player1 = new Player();
        player1.setUsername("Maximus");
        player1.setImageResource(R.drawable.bluehat);

        Player player2 = new Player();
        player2.setUsername("Susimaus");
        player2.setImageResource(R.drawable.greenhat);

        userList.add(player1);
        userList.add(player2);
    }

    private void onReturnBtnClick() {
        retCreateLobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Inside your Fragment class where you want to close and return
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.popBackStack();
            }
        });
    }
    private void onClickStart() {
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showGameBoardFragment();
            }
        });

        rulesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRulesFragment();
            }
        });
    }

    private void findViews(View view) {
        startGame = view.findViewById(R.id.btn_StartGame);
        retCreateLobby = view.findViewById(R.id.imgBtnRetCL);
        recyclerView = view.findViewById(R.id.lobbyPlayerRecyclerView);
        playerCount = view.findViewById(R.id.txtlistPlayerCount);
        rulesBtn = view.findViewById(R.id.button);
    }

    private void showGameBoardFragment() {

        GameBoardFragment gameBoardFragment = new GameBoardFragment();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(android.R.id.content, gameBoardFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void showRulesFragment(){

        RulesFragment rulesfragment = new RulesFragment();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(android.R.id.content,rulesfragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}