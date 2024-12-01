package com.example.labpsql.clr;

import com.example.labpsql.dto.request.*;
import com.example.labpsql.models.*;
import com.example.labpsql.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.example.labpsql.utils.ConsoleMessages.*;

@Component
@RequiredArgsConstructor
public class Clr implements CommandLineRunner {
    private final CountryService countryService;
    private final PlayerService playerService;
    private final ResultService resultService;
    private final SubjectService subjectService;
    private final TeamCompositionService teamCompositionService;
    private final TeamService teamService;
    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static final short SEARCH_COUNT = 20;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(WELCOME);

        while (true) {
            System.out.println(MENU);

            String input = bufferedReader.readLine();

            try {
                switch (input) {
                    case "1" -> insertCountry();
                    case "2" -> insertSubject();
                    case "3" -> insertPlayer();
                    case "4" -> insertTeam();
                    case "5" -> insertTeamComposition();
                    case "6" -> insertResult();
                    default -> System.out.println(INVALID_OPTION);
                }
            } catch (IOException ioe) {
                System.out.println("An error occurred while reading input: " + ioe.getMessage());
            } catch (RuntimeException re) {
                System.out.println("An error occurred: " + re.getMessage());
            }
        }
    }

    private void insertCountry() throws IOException {
        System.out.println("Enter a new country name:");
        String countryName = this.bufferedReader.readLine();

        Country createdCountry = countryService.saveCountry(countryName);
        System.out.println(createdCountry.toString());

        System.out.println("Country added successfully");
    }

    private void insertSubject() throws IOException {
        AddSubjectRequest.AddSubjectRequestBuilder builder = AddSubjectRequest.builder();
        System.out.println("Enter a new subject name:");
        builder.name(this.bufferedReader.readLine());

        System.out.println("Enter a new subject sport:");
        builder.sport(this.bufferedReader.readLine());

        Subject createdSubject = subjectService.saveSubject(builder.build());
        System.out.println(createdSubject.toString());

        System.out.println("Subject added successfully\n");
    }

    private void insertPlayer() throws IOException {
        AddPlayerRequest.AddPlayerRequestBuilder builder = AddPlayerRequest.builder();
        System.out.println("Insert a new player name:");
        builder.name(this.bufferedReader.readLine());

        System.out.println("Insert a new player country:");
        builder.country(countryService.findByName(this.bufferedReader.readLine()));

        System.out.println("Insert a new player gender (male/female/other):");
        builder.gender(Gender.valueOf(this.bufferedReader.readLine().toUpperCase()));

        System.out.println("Insert a new player date of birth (YYYY-MM-DD):");
        builder.birthDate(LocalDate.parse(this.bufferedReader.readLine()));

        System.out.println("Insert new player's subject:");
        builder.subject(search(subjectService.getAllSubjects()));

        Player createdPlayer = playerService.savePlayer(builder.build());
        System.out.println(createdPlayer.toString());

        System.out.println("Player created successfully\n");
    }

    private void insertTeam() throws IOException {
        AddTeamRequest.AddTeamRequestBuilder builder = AddTeamRequest.builder();
        System.out.println("Enter a new team name:");
        builder.name(this.bufferedReader.readLine());

        System.out.println("Enter a new team country:");
        builder.country(countryService.findByName(this.bufferedReader.readLine()));

        System.out.println("Enter a new team creation date (YYYY-MM-DD):");
        builder.foundedAt(LocalDate.parse(this.bufferedReader.readLine()));

        System.out.println("Enter a new team subject:");
        builder.subject(search(subjectService.getAllSubjects()));

        System.out.println("Enter a new team status (active/inactive/closed):");
        builder.status(TeamStatus.valueOf(this.bufferedReader.readLine().toUpperCase()));

        System.out.println("Enter a new team manager name:");
        builder.managerName(this.bufferedReader.readLine());

        Team createdTeam = teamService.saveTeam(builder.build());
        System.out.println(createdTeam.toString());

        System.out.println("Team created successfully\n");
    }

    private void insertTeamComposition() throws IOException {
        AddTeamCompositionRequest.AddTeamCompositionRequestBuilder builder = AddTeamCompositionRequest.builder();
        System.out.println("Enter a new team composition team name:");
        builder.team(search(teamService.getAllTeams()));

        System.out.println("Enter a new team composition employment year:");
        builder.year(Year.parse(this.bufferedReader.readLine()));

        System.out.println("Enter a new team composition player:");
        builder.player(search(playerService.getAllPlayers()));

        System.out.println("Enter a new team composition description:");
        builder.description(this.bufferedReader.readLine());

        TeamComposition createdTeamComposition = teamCompositionService.saveTeamComposition(builder.build());
        System.out.println(createdTeamComposition.toString());

        System.out.println("Team composition added successfully\n");
    }

    private void insertResult() throws IOException {
        AddResultRequest.AddResultRequestBuilder builder = AddResultRequest.builder();
        System.out.println("Enter a new record year:");
        builder.year(Year.parse(this.bufferedReader.readLine()));

        System.out.println("Enter a new record subject:");
        builder.subject(search(subjectService.getAllSubjects()));

        System.out.println("Enter a new record player's gender (male/female/other):");
        builder.gender(Gender.valueOf(this.bufferedReader.readLine().toUpperCase()));

        System.out.println("Enter a new record result:");
        builder.result(this.bufferedReader.readLine());

        System.out.println("Enter a new record location:");
        builder.location(this.bufferedReader.readLine());

        System.out.println("Enter a new record type (guinness/published/unpublished):");
        builder.recordType(RecordType.valueOf(this.bufferedReader.readLine().toUpperCase()));

        System.out.println("Enter a new record's players:");
        builder.players(playersSelection());

        Result createdResult = resultService.saveResult(builder.build());
        System.out.println(createdResult.toString());

        System.out.println("Result added successfully\n");
    }

    private List<Player> playersSelection() throws IOException {
        List<Player> chosenPlayers = new ArrayList<>();
        List<Player> allPlayers = playerService.getAllPlayers();

        while (true) {
            Player chosenPlayer = search(allPlayers);
            chosenPlayers.add(chosenPlayer);
            allPlayers.remove(chosenPlayer);

            if (allPlayers.isEmpty()) break;

            System.out.println("Choose another player (yes/no):");
            String input = bufferedReader.readLine();

            if (input.equalsIgnoreCase("no")) {
                break;
            }
        }

        return chosenPlayers;
    }

    private <T extends BaseEntity> T search(List<T> entities) throws IOException {
        Iterator<T> iterator = entities.iterator();
        short counter = 0;
        String input;

        while (iterator.hasNext()) {
            System.out.println(counter++ + ": " + iterator.next().toString());
            if (isSearchCountReached(counter)) {
                while (true) {
                    input = bufferedReader.readLine();

                    if (input.equalsIgnoreCase("more")) {
                        break;
                    }
                    if (input.equalsIgnoreCase("exit")) {
                        throw new RuntimeException("Search aborted");
                    }
                    try {
                        int index = Integer.parseInt(input);
                        return entities.get(index);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid index, 'more' to search further or 'exit' to abort.");
                    }
                }
            }
        }
        System.out.println("End of table! Enter the index of the entity you chosen or 'exit' to abort.");
        while (true) {
            input = bufferedReader.readLine();

            if (input.equalsIgnoreCase("exit")) {
                throw new RuntimeException("Search aborted");
            }
            try {
                int index = Integer.parseInt(input);
                return entities.get(index);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid index, or 'exit' to abort.");
            }
        }
    }

    private boolean isSearchCountReached(short counter) {
        return counter % SEARCH_COUNT == 0;
    }
}