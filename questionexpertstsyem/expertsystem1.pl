% expertsystem1.pl

% Love Language Assessment
symptom('One_on_One_Time', "Do you prefer spending one-on-one time with people?").
symptom('Practical_Help', "Do you feel loved when someone gives you practical help?").
symptom('Receiving_Gifts', "Do you enjoy receiving gifts?").
symptom('Physical_Touch', "Do you feel loved when someone close to you puts an arm around you?").
symptom('Importance_of_Gifts', "Are gifts important symbols of love to you?").
symptom('Quality_Time_with_Family', "Do you like spending time with friends and family?").
symptom('Words_of_Affirmation', "Do words of acceptance mean a lot to you?").
symptom('Affirming_Notes_and_Texts', "Do you enjoy receiving affirming notes and texts?").
symptom('Kind_Words', "Do you feel loved when kind words are spoken to you?").
symptom('Actions_vs_Words', "Do actions affect you more than words?").
symptom('Hugs', "Do hugs make you feel connected and valued?").
symptom('Praise_vs_Criticism', "Do you value praise over criticism?").
symptom('Shared_Activities', "Do you feel close to someone when you're talking or doing something together?").
symptom('Physical_Affection', "Do you feel closer to friends or family when they touch you often?").
symptom('Gift_Preference', "Do you prefer receiving several small gifts over one large gift?").

% Love Languages
love_language('Quality_Time', ['One_on_One_Time', 'Quality_Time_with_Family']).
love_language('Acts_of_Service', ['Practical_Help']).
love_language('Receiving_Gifts', ['Receiving_Gifts', 'Importance_of_Gifts']).
love_language('Physical_Touch', ['Physical_Touch']).
love_language('Words_of_Affirmation', ['Words_of_Affirmation', 'Affirming_Notes_and_Texts', 'Kind_Words']).
love_language('Physical_Affection', ['Hugs']).
love_language('Shared_Activities', ['Shared_Activities']).
love_language('Gift_Preference', ['Gift_Preference']).

% Checks if a person's symptoms match a love language exactly
matches_language(Language, PersonSymptoms) :-
    love_language(Language, RequiredSymptoms),
    subset(RequiredSymptoms, PersonSymptoms), % Check if all required symptoms are present
    subset(PersonSymptoms, RequiredSymptoms), % Check if no extra symptoms are present
    length(RequiredSymptoms, _Length1),
    length(PersonSymptoms, _Length2). % Check if the lengths of the symptom lists are equal

% Given a list of person's symptoms, find matching love languages and assign response messages
identify_love_language(PersonSymptoms, LoveLanguage) :-
    findall(Language, matches_language(Language, PersonSymptoms), Languages),
    (   Languages = [] ->
        LoveLanguage = "No matching love language found.";
        (   member('Quality_Time', Languages) ->
            LoveLanguage = "Your responses suggest Quality Time as your primary love language.";
            member('Acts_of_Service', Languages) ->
            LoveLanguage = "Your responses suggest Acts of Service as your primary love language.";
            member('Receiving_Gifts', Languages) ->
            LoveLanguage = "Your responses suggest Receiving Gifts as your primary love language.";
            member('Physical_Touch', Languages) ->
            LoveLanguage = "Your responses suggest Physical Touch as your primary love language.";
            member('Words_of_Affirmation', Languages) ->
            LoveLanguage = "Your responses suggest Words of Affirmation as your primary love language.";
            member('Physical_Affection', Languages) ->
            LoveLanguage = "Your responses suggest Physical Affection as your primary love language.";
            member('Shared_Activities', Languages) ->
            LoveLanguage = "Your responses suggest Shared Activities as your primary love language.";
            member('Gift_Preference', Languages) ->
            LoveLanguage = "Your responses suggest Gift Preference as your primary love language."
        )
    ).
