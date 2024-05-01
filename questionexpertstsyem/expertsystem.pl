% Extended symptoms and diseases with simple severity scoring
symptom('Anger_Expression', "Do you have serious arguments with your loved one, sometimes for no reason?").
symptom('Conflict_Resolution', "Are you very good at being quick to talk to someone who offends you so you can work out the issue?").
symptom('Forgiveness', "Do you occasionally feel regret about how you express your anger?").
symptom('Frustration_Management', "Do little things bother you very much?").
symptom('Awareness_and_Control', "Have you been so angry at times that you couldn't even remember some of the things you said or did?").
symptom('Conflict_Avoidance', "Do you tend to get in many arguments?").
symptom('Temperament', "Are some people afraid of your bad temper?").
symptom('Emotional_Regulation', "Do you often break things when you are angry?").
symptom('Post_Anger_Behavior', "After getting angry, are you still able to act lovingly toward those around you?").
symptom('Resolution_and_Reflection', "After an argument, do you often find yourself wishing you had thought of a better way to respond?").
symptom('Response_Evaluation', "Do people tend to think you overreact when you’re angry?").

% Diseases with their symptoms for different levels of anger
disease('low_angry', ['Anger_Expression', 'Forgiveness', 'Frustration_Management', 'Awareness_and_Control', 'Resolution_and_Reflection']).
disease('medium_angry', ['Anger_Expression', 'Frustration_Management', 'Temperament', 'Emotional_Regulation', 'Resolution_and_Reflection', 'Response_Evaluation']).
disease('high_angry', ['Anger_Expression', 'Frustration_Management', 'Temperament', 'Emotional_Regulation', 'Post_Anger_Behavior', 'Resolution_and_Reflection', 'Response_Evaluation']).

% Checks if a patient's symptoms match a disease exactly
matches_disease(Disease, PatientSymptoms) :-
    disease(Disease, RequiredSymptoms),
    subset(RequiredSymptoms, PatientSymptoms), % Check if all required symptoms are present
    subset(PatientSymptoms, RequiredSymptoms), % Check if no extra symptoms are present
    length(RequiredSymptoms, Length1),
    length(PatientSymptoms, Length2),
    Length1 = Length2. % Check if the lengths of the symptom lists are equal

% Given a list of patient symptoms, find matching diseases and assign response messages
diagnose(PatientSymptoms, Diagnosis) :-
    findall(Disease, matches_disease(Disease, PatientSymptoms), Diseases),
    (   Diseases = [] ->
        Diagnosis = "No matching diagnosis found.";
        (   member(high_angry, Diseases) ->
            Diagnosis = "Your responses indicate a high level of anger. It may be beneficial to seek professional help or therapy.";
            member(medium_angry, Diseases) ->
            Diagnosis = "Your responses suggest a moderate level of anger. Consider exploring anger management techniques to help improve your coping skills.";
            member(low_angry, Diseases) ->
            Diagnosis = "Your responses indicate a low level of anger. Keep practicing healthy anger management habits to maintain a positive outlook."
        )
    ).
