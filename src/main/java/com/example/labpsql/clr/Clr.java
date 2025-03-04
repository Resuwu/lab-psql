package com.example.labpsql.clr;

import com.example.labpsql.dto.request.*;
import com.example.labpsql.models.*;
import com.example.labpsql.services.*;
import com.example.labpsql.utils.ObjectTypes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import static com.example.labpsql.utils.ObjectTypes.*;
import static com.example.labpsql.utils.SearchNavigation.EXIT;
import static com.example.labpsql.utils.SearchNavigation.MORE;

@Component
@RequiredArgsConstructor
@Slf4j
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
                    case "7" -> displayTable(countryService.getAllCountries(), getPrintHeaderMessage(COUNTRY));
                    case "8" -> displayTable(playerService.getAllPlayers(), getPrintHeaderMessage(PLAYER));
                    case "9" -> displayTable(resultService.getAllResults(), getPrintHeaderMessage(RECORD));
                    case "10" -> displayResultsByYear();
                    case "11" -> displayTable(subjectService.getAllSubjects(), getPrintHeaderMessage(SUBJECT));
                    case "12" -> displayTable(teamCompositionService.getAllTeamCompositions(), getPrintHeaderMessage(TEAM_COMPOSITION));
                    case "13" -> displayTable(teamService.getAllTeams(), getPrintHeaderMessage(TEAM));
                    case "14" -> exitApplication();
                    default -> System.out.println(INVALID_OPTION);
                }
            } catch (IOException ioe) {
                log.error("An error occurred while reading input: {}", ioe.getMessage());
            } catch (RuntimeException re) {
                log.error("An error occurred: {}", re.getMessage());
            }

            System.out.print(WAITING_FOR_ENTER);
            bufferedReader.readLine();
        }
    }

    private void insertCountry() throws IOException {
        System.out.println("Enter a new country name:");
        String countryName = this.bufferedReader.readLine();

        Country createdCountry = countryService.saveCountry(countryName);
        printResult(createdCountry, COUNTRY.name);
    }

    private void insertSubject() throws IOException {
        AddSubjectRequest.AddSubjectRequestBuilder builder = AddSubjectRequest.builder();
        System.out.println("Enter a new subject name:");
        builder.name(this.bufferedReader.readLine());

        System.out.println("Enter a new subject sport:");
        builder.sport(this.bufferedReader.readLine());

        Subject createdSubject = subjectService.saveSubject(builder.build());
        printResult(createdSubject, SUBJECT.name);
    }

    private void insertPlayer() throws IOException {
        AddPlayerRequest.AddPlayerRequestBuilder builder = AddPlayerRequest.builder();
        System.out.println("Enter a new player name:");
        builder.name(this.bufferedReader.readLine());

        System.out.println("Enter a new player country:");
        builder.country(countryService.findByName(this.bufferedReader.readLine()));

        System.out.println("Enter a new player gender (male/female/other):");
        builder.gender(Gender.valueOf(this.bufferedReader.readLine().toUpperCase()));

        System.out.println("Enter a new player date of birth (YYYY-MM-DD):");
        builder.birthDate(LocalDate.parse(this.bufferedReader.readLine()));

        System.out.println("Enter new player's subject:");
        builder.subject(search(subjectService.getAllSubjects()));

        Player createdPlayer = playerService.savePlayer(builder.build());
        printResult(createdPlayer, PLAYER.name);
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
        printResult(createdTeam, TEAM.name);
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
        printResult(createdTeamComposition, TEAM_COMPOSITION.name);
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
        printResult(createdResult, RECORD.name);
    }

    public <T extends BaseEntity> void displayTable(List<T> objects, String message) {
        System.out.println(message);
        for (T object : objects) {
            System.out.println(object.toString());
        }
    }

    public void displayResultsByYear() throws IOException {
        System.out.println("Enter a year to search results:");
        Year year = Year.parse(this.bufferedReader.readLine());

        List<Result> resultsByYear = resultService.findAllByYear(year);
        System.out.println("Results for year " + year + ":");
        for (Result result : resultsByYear) {
            System.out.println(result.toString());
        }
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
                System.out.println("Please enter an index, 'more' to search further or 'exit' to abort.");
                while (true) {
                    input = bufferedReader.readLine();

                    if (MORE.value.equalsIgnoreCase(input)) {
                        break;
                    }
                    if (EXIT.value.equalsIgnoreCase(input)) {
                        throw new RuntimeException(SEARCH_ABORT);
                    }
                    try {
                        int index = Integer.parseInt(input);
                        return entities.get(index);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid index, 'more' to search further or 'exit' to abort.");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(INDEX_OUT_OF_BOUNDS);
                    }
                }
            }
        }
        System.out.println("End of table! Enter the index of the entity you chosen or 'exit' to abort.");
        while (true) {
            input = bufferedReader.readLine();

            if (EXIT.value.equalsIgnoreCase(input)) {
                throw new RuntimeException(SEARCH_ABORT);
            }
            try {
                int index = Integer.parseInt(input);
                return entities.get(index);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid index, or 'exit' to abort.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println(INDEX_OUT_OF_BOUNDS);
            }
        }
    }

    private <T extends BaseEntity> void printResult(T object, String objectName) {
        if (object != null) {
            System.out.println(object);
            System.out.println(objectName + " added successfully");
        }
    }

    private boolean isSearchCountReached(short counter) {
        return counter % SEARCH_COUNT == 0;
    }

    private void exitApplication() {
        System.out.println(EXITING_APPLICATION);
        System.exit(0);
    }

    private String getPrintHeaderMessage(ObjectTypes type) {
        return "%s list:".formatted(type.name);
    }
}