package com.example.Shwas.data.dataPersistence.RoomDb.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.Shwas.domain.model.BreathType
import com.example.Shwas.domain.model.TimedInstruction

@Entity(
    tableName = "breathing_steps_entity",
    foreignKeys = [ForeignKey(
        entity = BreathingExerciseEntity::class,
        parentColumns = ["id"],
        childColumns = ["id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class BreathingStepsEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "prep_prompt") val prepPrompt: Map<String, Map<String, Long>>,
    @ColumnInfo(name = "initial_prompt") val beginPrompt: Map<String, Map<String, Long>>,
    @ColumnInfo(name = "continue_prompt") val continuePrompt: Map<String, Map<String, Long>>,
    @ColumnInfo(name = "end_prompt") val endPrompt: Map<String, Map<String, Long>>,
    @ColumnInfo(name = "number_of_cycle") val numberOfCycle: Int = 7
) {
    companion object {
        val breathingExerciseSteps = listOf(
            BreathingStepsEntity(
                id = "Nadi Shodhana",
                prepPrompt = mapOf(
                    "en" to mapOf(
                        "Sit comfortably with spine straight. Place left hand on left knee, palm up." to 3000L,
                        "Bring right hand to nose. Thumb on right nostril, ring and little fingers on left." to 3000L,
                        "We'll begin alternate nostril breathing. Close your eyes." to 2000L,
                    ),
                    "hi" to mapOf(
                        "आराम से बैठें, रीढ़ सीधी रखें। बायाँ हाथ बाएं घुटने पर रखें, हथेली ऊपर की ओर।" to 3000L,
                        "दाहिने हाथ को नाक के पास लाएँ। अंगूठा दाहिनी नासिका पर, अनामिका और छोटी उंगली बाईं पर रखें।" to 3000L,
                        "अब हम अनुलोम-विलोम प्राणायाम शुरू करेंगे। आंखें बंद करें।" to 2000L,
                    )
                ),
                beginPrompt = mapOf(
                    "en" to mapOf(
                        "Press right nostril closed with thumb." to 1000L,
                        "Exhale completely through left nostril." to 4000L,
                        "Now inhale deeply through left nostril." to 4000L,
                        "Close left nostril with ring finger." to 1000L,
                        "Open right nostril and exhale slowly." to 4000L,
                        "Inhale deeply through right nostril." to 4000L,
                    ),
                    "hi" to mapOf(
                        "अंगूठे से दाहिनी नासिका को बंद करें।" to 1000L,
                        "बाईं नासिका से पूरी तरह श्वास छोड़ें।" to 4000L,
                        "अब बाईं नासिका से गहरी श्वास लें।" to 4000L,
                        "अनामिका से बाईं नासिका बंद करें।" to 1000L,
                        "दाहिनी नासिका खोलें और धीरे-धीरे श्वास छोड़ें।" to 4000L,
                        "दाहिनी नासिका से गहरी श्वास लें।" to 4000L,
                    )
                ),
                continuePrompt = mapOf(
                    "en" to mapOf(
                        "Close right, exhale left." to 5000L,
                        "Inhale left." to 4000L,
                        "Close left, exhale right." to 5000L,
                        "Inhale right." to 4000L,
                    ),
                    "hi" to mapOf(
                        "दाहिनी नासिका बंद करें|" to 1000L,
                        "बाईं नासिका से श्वास छोड़ें।" to 4000L,
                        "बाईं नासिका से श्वास लें।" to 4000L,
                        "बाईं नासिका बंद करें।" to 1000L,
                        "दाहिनी नासिका से श्वास छोड़ें।" to 4000L,
                        "दाहिनी नासिका से श्वास लें।" to 4000L,
                    )
                ),
                endPrompt = mapOf(
                    "en" to mapOf(
                        "Release your hand to your knee." to 2000L,
                        "You have completed 9 rounds of Nadi Shodhan." to 3000L,
                        "Notice the calmness in your mind and body." to 4000L,
                        "When ready, gently open your eyes." to 3000L
                    ),
                    "hi" to mapOf(
                        "हाथ को घुटने पर रखें और आराम करें।" to 2000L,
                        "आपने नाड़ी शोधन के 9 चक्र पूरे कर लिए हैं।" to 3000L,
                        "मन और शरीर की शांति को महसूस करें।" to 4000L,
                        "जब तैयार हों, धीरे-धीरे आँखें खोलें।" to 3000L
                    )
                ),
                numberOfCycle = 7
            ),
            BreathingStepsEntity(
                id = "Active Yogic Breathing",
                prepPrompt = mapOf(
                    "en" to mapOf(
                        "Find a comfortable posture. Sit or lie down with a straight spine." to 4000L,
                        "Bring your attention to your breath. Feel the air moving in and out." to 4000L,
                    ),
                    "hi" to mapOf(
                        "आरामदायक स्थिति में बैठें या लेट जाएँ। रीढ़ को सीधा रखें।" to 4000L,
                        "अपने ध्यान को श्वास पर केंद्रित करें। हवा के आने-जाने की अनुभूति करें।" to 4000L,
                    )
                ),
                beginPrompt = mapOf(
                    "en" to mapOf(
                        "Now, inhale deeply into the belly, then ribs, then chest." to 5000L,
                        "gently hold your breath for a few seconds." to 3000L,
                        "Now exhale slowly and fully, using your abdominal muscles." to 5000L
                    ),
                    "hi" to mapOf(
                        "अब गहरी श्वास लें — पहले पेट, फिर पसलियाँ, फिर छाती भरें।" to 5000L,
                        "कुछ क्षण के लिए श्वास को रोकें।" to 3000L,
                        "अब धीरे और पूरी तरह से श्वास छोड़ें, पेट की मांसपेशियों का उपयोग करें।" to 5000L,
                    )
                ),
                continuePrompt = mapOf(
                    "en" to mapOf(
                        "inhale" to 5000L,
                        "hold" to 3000L,
                        "exhale" to 5000L
                    ),
                    "hi" to mapOf(
                        "श्वास लें" to 5000L,
                        "श्वास को रोकें" to 3000L,
                        "श्वास छोड़ें" to 5000L,
                    )
                ),
                endPrompt = mapOf(
                    "en" to mapOf(
                        "Observe the calmness in your body and mind." to 3000L,
                        "When ready, gently open your eyes." to 2000L
                    ),
                    "hi" to mapOf(
                        "अपने शरीर और मन में आई शांति को महसूस करें।" to 3000L,
                        "जब तैयार हों, धीरे से अपनी आँखें खोलें।" to 2000L
                    )
                ),
                numberOfCycle = 7
            ),
            BreathingStepsEntity(
                id = "Bhastrika",
                prepPrompt = mapOf(
                    "en" to mapOf(
                        "Sit comfortably in Siddhasana, Sukhasana, or Padmasana with spine upright." to 4000L,
                        "Relax your body and become aware of your natural breath." to 3000L,
                        "Prepare for deep and forceful breathing." to 3000L
                    ),
                    "hi" to mapOf(
                        "सिद्धासन, सुखासन या पद्मासन में रीढ़ सीधी रखकर आराम से बैठें।" to 4000L,
                        "शरीर को ढीला छोड़ें और अपनी सामान्य श्वास पर ध्यान दें।" to 3000L,
                        "गहरी और तेज़ श्वास की तैयारी करें।" to 3000L
                    )
                ),
                beginPrompt = mapOf(
                    "en" to mapOf(
                        "Inhale through both nostrils and exhale forcefully making a hissing sound." to 4000L,
                        "Continue rapid inhalation and exhalation with a rhythmic flow." to 4000L
                    ),
                    "hi" to mapOf(
                        "दोनों नासिकाओं से श्वास लें और जोर से साँय-साँय की ध्वनि करते हुए छोड़ें।" to 4000L,
                        "तेज़ गति से श्वास लेना और छोड़ना लयबद्ध रूप से जारी रखें।" to 4000L
                    )
                ),
                continuePrompt = mapOf(
                    "en" to mapOf(
                        "Repeat rapid breathing for 3–4 rounds with spine straight." to 4000L,
                        "Maintain comfort throughout the breathing." to 3000L
                    ),
                    "hi" to mapOf(
                        "सीधी रीढ़ के साथ 3–4 चक्र तक तेज़ श्वास जारी रखें।" to 4000L,
                        "पूरे अभ्यास में सहजता बनाए रखें।" to 3000L
                    )
                ),
                endPrompt = mapOf(
                    "en" to mapOf(
                        "Take one deep breath in, hold using internal locks, then exhale slowly and silently." to 5000L,
                        "Close your eyes and observe inner stillness." to 3000L,
                        "Relax in Corpse Pose and feel the breath." to 3000L
                    ),
                    "hi" to mapOf(
                        "एक गहरी श्वास लें, बंध लगाकर कुछ क्षण रोकें, फिर धीरे-धीरे बिना ध्वनि के छोड़ें।" to 5000L,
                        "आँखें बंद करें और आंतरिक शांति का अनुभव करें।" to 3000L,
                        "शवासन में विश्राम करें और श्वास को महसूस करें।" to 3000L
                    )
                ),
                numberOfCycle = 3
            ),
            BreathingStepsEntity(
                id = "Chandra Bhedana",
                prepPrompt = mapOf(
                    "en" to mapOf(
                        "Sit in a comfortable meditative position or on a chair with a straight spine." to 4000L,
                        "Relax your shoulders, open your chest, and place your hands gently on your knees." to 3000L,
                        "Ensure there are no nasal blockages. Breathe comfortably through both nostrils." to 3000L,
                        "Place your left hand on your left knee. Bring your right hand near your nose for nostril control." to 3000L
                    ),
                    "hi" to mapOf(
                        "आरामदायक ध्यान मुद्रा में या कुर्सी पर सीधे बैठें।" to 4000L,
                        "कंधों को ढीला छोड़ें, छाती को खोलें और हाथों को घुटनों पर रखें।" to 3000L,
                        "सुनिश्चित करें कि नासिका अवरोध नहीं है। दोनों नासिकाओं से आसानी से श्वास लें।" to 3000L,
                        "बायाँ हाथ बाएं घुटने पर रखें। दाहिना हाथ नाक के पास लाएँ नासिका नियंत्रण के लिए।" to 3000L
                    )
                ),
                beginPrompt = mapOf(
                    "en" to mapOf(
                        "Close your right nostril using your right thumb." to 2000L,
                        "Inhale slowly and deeply through the left nostril." to 4000L,
                        "Optionally, hold your breath for a moment." to 2000L,
                        "Close your left nostril with your ring finger." to 2000L,
                        "Open your right nostril and exhale slowly and smoothly." to 4000L
                    ),
                    "hi" to mapOf(
                        "दाहिनी नासिका को दाहिने अंगूठे से बंद करें।" to 2000L,
                        "बाईं नासिका से धीरे और गहराई से श्वास लें।" to 4000L,
                        "इच्छानुसार कुछ क्षण श्वास रोक सकते हैं।" to 2000L,
                        "बाईं नासिका को अनामिका से बंद करें।" to 2000L,
                        "दाहिनी नासिका खोलें और धीरे-धीरे श्वास छोड़ें।" to 4000L
                    )
                ),
                continuePrompt = mapOf(
                    "en" to mapOf(
                        "Inhale through the left nostril." to 4000L,
                        "Exhale through the right nostril." to 4000L
                    ),
                    "hi" to mapOf(
                        "बाईं नासिका से श्वास लें।" to 4000L,
                        "दाहिनी नासिका से श्वास छोड़ें।" to 4000L
                    )
                ),
                endPrompt = mapOf(
                    "en" to mapOf(
                        "Return your hands to your knees and breathe naturally." to 3000L,
                        "Remain still and relaxed in a seated position." to 3000L,
                        "Gently open your eyes when ready." to 3000L
                    ),
                    "hi" to mapOf(
                        "हाथों को घुटनों पर रखें और सामान्य रूप से श्वास लें।" to 3000L,
                        "बैठे रहें और शरीर को ढीला छोड़ें।" to 3000L,
                        "जब तैयार हों, धीरे से आँखें खोलें।" to 3000L
                    )
                ),
                numberOfCycle = 6
            ),
            BreathingStepsEntity(
                id = "Ujjayi",
                prepPrompt = mapOf(
                    "en" to mapOf(
                        "Sit comfortably in Easy Pose. Close your eyes and relax your body." to 4000L,
                        "Let your mouth open slightly, relaxing your jaw and tongue." to 3000L
                    ),
                    "hi" to mapOf(
                        "सुखासन में आराम से बैठें। आँखें बंद करें और शरीर को ढीला छोड़ें।" to 4000L,
                        "मुख थोड़ा खुला रखें, जबड़ा और जीभ ढीली छोड़ें।" to 3000L
                    )
                ),
                beginPrompt = mapOf(
                    "en" to mapOf(
                        "Inhale and exhale deeply through your mouth." to 4000L,
                        "As you exhale, slightly constrict your throat to make an 'ahhh' sound like whispering." to 4000L
                    ),
                    "hi" to mapOf(
                        "मुख के माध्यम से गहरी श्वास लें और छोड़ें।" to 4000L,
                        "श्वास छोड़ते समय गले को थोड़ा संकुचित करें और 'आह' जैसी ध्वनि निकालें।" to 4000L
                    )
                ),
                continuePrompt = mapOf(
                    "en" to mapOf(
                        "Maintain throat constriction during inhalation as well, creating an ocean-like sound." to 4000L,
                        "Now close your mouth and breathe only through your nose with the same throat control." to 4000L,
                        "Focus on the sound of the breath, smooth and continuous." to 4000L
                    ),
                    "hi" to mapOf(
                        "श्वास लेते समय भी गले को हल्का संकुचित रखें और समुद्र जैसी ध्वनि महसूस करें।" to 4000L,
                        "अब मुँह बंद करें और केवल नाक से उसी गले के संकुचन के साथ श्वास लें।" to 4000L,
                        "श्वास की ध्वनि पर ध्यान केंद्रित करें — कोमल और निरंतर।" to 4000L
                    )
                ),
                endPrompt = mapOf(
                    "en" to mapOf(
                        "Let each inhale fill your lungs fully, and each exhale be complete." to 4000L,
                        "Release Ujjayi breath and relax in Corpse Pose." to 3000L
                    ),
                    "hi" to mapOf(
                        "प्रत्येक श्वास से फेफड़ों को पूरी तरह भरने दें और श्वास पूर्ण रूप से छोड़ें।" to 4000L,
                        "उज्जायी श्वास को छोड़ें और शवासन में विश्राम करें।" to 3000L
                    )
                ),
                numberOfCycle = 1
            ),
            BreathingStepsEntity(
                id = "Shiitali Kumbhaka",
                prepPrompt = mapOf(
                    "en" to mapOf(
                        "Sit cross-legged or in a comfortable position with spine long and shoulders relaxed." to 4000L,
                        "Take 2–3 deep breaths through your nose to center yourself." to 3000L
                    ),
                    "hi" to mapOf(
                        "रीढ़ को सीधा और कंधों को ढीला रखते हुए आरामदायक स्थिति में बैठें।" to 4000L,
                        "अपने आप को स्थिर करने के लिए नाक से 2–3 गहरी श्वास लें।" to 3000L
                    )
                ),
                beginPrompt = mapOf(
                    "en" to mapOf(
                        "Roll your tongue into a tube and stick it slightly out between pursed lips. If you can't, make an 'O' shape with your lips." to 5000L,
                        "Keep the tongue behind lower teeth or place it on the roof of your mouth if not rolled." to 4000L
                    ),
                    "hi" to mapOf(
                        "जीभ को मोड़ें और उसे होठों के बीच से थोड़ा बाहर निकालें। यदि संभव न हो तो होठों से 'ओ' आकार बनाएं।" to 5000L,
                        "यदि जीभ मोड़ना न आए तो उसे नीचे के दांतों के पीछे या तालु पर रखें।" to 4000L
                    )
                ),
                continuePrompt = mapOf(
                    "en" to mapOf(
                        "Inhale slowly through the tongue tube or lip 'O' shape, filling chest and belly." to 5000L,
                        "Close your mouth and exhale slowly through your nose." to 4000L
                    ),
                    "hi" to mapOf(
                        "जीभ की नली या 'ओ' आकार से धीरे-धीरे श्वास लें, छाती और पेट को भरें।" to 5000L,
                        "मुँह बंद करें और नाक से धीरे से श्वास छोड़ें।" to 4000L
                    )
                ),
                endPrompt = mapOf(
                    "en" to mapOf(
                        "Relax and observe the silence within you." to 3000L,
                        "If comfortable, try increasing to 26 rounds in the morning and evening." to 4000L
                    ),
                    "hi" to mapOf(
                        "आराम करें और अपने भीतर की शांति को महसूस करें।" to 3000L,
                        "यदि सहज हो तो सुबह और शाम 26 बार तक अभ्यास करें।" to 4000L
                    )
                ),
                numberOfCycle = 10
            ),
            BreathingStepsEntity(
                id = "Siitkari Kumbhaka",
                prepPrompt = mapOf(
                    "en" to mapOf(
                        "Sit comfortably in a classic pranayama posture. Close your eyes, rest hands on knees, and observe your breath." to 4000L,
                        "Exhale through the mouth to release stagnant air from the lungs." to 3000L
                    ),
                    "hi" to mapOf(
                        "प्राणायाम मुद्रा में आराम से बैठें। आँखें बंद करें, हाथ घुटनों पर रखें और अपनी श्वास का निरीक्षण करें।" to 4000L,
                        "मुख से श्वास छोड़ें ताकि फेफड़ों की बासी हवा बाहर निकल सके।" to 3000L
                    )
                ),
                beginPrompt = mapOf(
                    "en" to mapOf(
                        "Part your lips and gently touch upper teeth to lower teeth. Keep tongue relaxed inside mouth." to 4000L,
                        "Inhale slowly through the teeth, creating a soft hissing sound." to 4000L
                    ),
                    "hi" to mapOf(
                        "होठों को खोलें और ऊपरी व निचले दांतों को हल्के से स्पर्श करें। जीभ को मुँह के अंदर ढीला छोड़ें।" to 4000L,
                        "दांतों के बीच से धीरे-धीरे श्वास लें जिससे हल्की स-स-स ध्वनि बने।" to 4000L
                    )
                ),
                continuePrompt = mapOf(
                    "en" to mapOf(
                        "Close your mouth and touch the tongue tip to the roof of the mouth. Hold the breath for a few seconds." to 4000L,
                        "Exhale slowly through both nostrils with control." to 4000L
                    ),
                    "hi" to mapOf(
                        "मुँह बंद करें और जीभ की नोक को तालु से स्पर्श कराएँ। कुछ क्षण श्वास रोकें।" to 4000L,
                        "दोनों नासिकाओं से धीरे और नियंत्रित श्वास छोड़ें।" to 4000L
                    )
                ),
                endPrompt = mapOf(
                    "en" to mapOf(
                        "Sit in Easy Pose or lie down in Corpse Pose and breathe normally to conclude." to 4000L
                    ),
                    "hi" to mapOf(
                        "अंत में सुखासन में बैठें या शवासन में लेटें और सामान्य रूप से श्वास लें।" to 4000L
                    )
                ),
                numberOfCycle = 10
            ),
            BreathingStepsEntity(
                id = "Surya Bhedana",
                prepPrompt = mapOf(
                    "en" to mapOf(
                        "Sit in a meditation posture like Padmasana. Rest your left hand on your thigh and exhale fully through the mouth." to 4000L
                    ),
                    "hi" to mapOf(
                        "पद्मासन जैसी ध्यान मुद्रा में बैठें। बायाँ हाथ जाँघ पर रखें और मुँह से पूरी श्वास बाहर छोड़ें।" to 4000L
                    )
                ),
                beginPrompt = mapOf(
                    "en" to mapOf(
                        "Close your left nostril with your ring finger. Inhale slowly through the right nostril." to 4000L,
                        "After inhaling, close the right nostril as well and hold the breath for a few counts." to 4000L
                    ),
                    "hi" to mapOf(
                        "अनामिका से बाईं नासिका बंद करें। दाहिनी नासिका से धीरे-धीरे श्वास लें।" to 4000L,
                        "श्वास लेने के बाद दाहिनी नासिका भी बंद करें और कुछ क्षण श्वास रोकें।" to 4000L
                    )
                ),
                continuePrompt = mapOf(
                    "en" to mapOf(
                        "Keep the right nostril closed and exhale through the left nostril." to 4000L,
                        "Hold the breath again after exhalation for a few counts." to 3000L
                    ),
                    "hi" to mapOf(
                        "दाहिनी नासिका बंद रखें और बाईं नासिका से श्वास छोड़ें।" to 4000L,
                        "इच्छानुसार, श्वास छोड़ने के बाद फिर से कुछ क्षण रोक सकते हैं।" to 3000L
                    )
                ),
                endPrompt = mapOf(
                    "en" to mapOf(
                        "After finishing, rest your hands on thighs and sit in Sukhasana for 3–5 minutes, breathing naturally." to 4000L
                    ),
                    "hi" to mapOf(
                        "अंत में हाथों को जांघों पर रखें और सुखासन में 3–5 मिनट तक सामान्य रूप से श्वास लें।" to 4000L
                    )
                ),
                numberOfCycle = 12
            ),
            BreathingStepsEntity(
                id = "Brahmari",
                prepPrompt = mapOf(
                    "en" to mapOf(
                        "Sit comfortably in Siddhasana, Sukhasana, or Padmasana with a straight spine. Relax and observe your breath." to 4000L,
                        "Close your eyes, relax facial muscles, and slightly tilt your head upward." to 3000L,
                        "Place your hands on your knees and breathe naturally." to 3000L
                    ),
                    "hi" to mapOf(
                        "सिद्धासन, सुखासन या पद्मासन में रीढ़ सीधी रखकर आराम से बैठें। श्वास पर ध्यान दें।" to 4000L,
                        "आँखें बंद करें, चेहरे की मांसपेशियों को ढीला छोड़ें और गर्दन को थोड़ा ऊपर की ओर रखें।" to 3000L,
                        "हाथों को घुटनों पर रखें और सामान्य रूप से श्वास लें।" to 3000L
                    )
                ),
                beginPrompt = mapOf(
                    "en" to mapOf(
                        "Close your ears with your thumbs, place other fingers gently over your eyes." to 4000L,
                        "Take a deep breath in through both nostrils." to 3000L,
                        "Begin to exhale with a gentle humming sound like a bee, keeping the mouth closed." to 4000L
                    ),
                    "hi" to mapOf(
                        "अंगूठों से कानों को बंद करें और बाकी उंगलियाँ आँखों पर रखें।" to 4000L,
                        "दोनों नासिकाओं से गहरी श्वास लें।" to 3000L,
                        "मुँह बंद रखते हुए मधुमक्खी जैसी गुनगुनाहट के साथ श्वास छोड़ें।" to 4000L
                    )
                ),
                continuePrompt = mapOf(
                    "en" to mapOf(
                        "As you exhale, observe the contraction of your chest and abdomen with the sound." to 4000L,
                        "Remain still with fingers in place and listen to the internal vibration after the exhale." to 4000L
                    ),
                    "hi" to mapOf(
                        "श्वास छोड़ते समय छाती और पेट की संकुचन को महसूस करें।" to 4000L,
                        "श्वास छोड़ने के बाद स्थिर रहें और आंतरिक कंपन को सुनें।" to 4000L
                    )
                ),
                endPrompt = mapOf(
                    "en" to mapOf(
                        "Once complete, release your hands and relax in Corpse Pose to absorb the effects." to 4000L
                    ),
                    "hi" to mapOf(
                        "अभ्यास पूर्ण होने पर हाथों को छोड़ें और शवासन में विश्राम करें।" to 4000L
                    )
                ),
                numberOfCycle = 8
            )

        )

        val empty = BreathingStepsEntity(
            id = "empty",
            prepPrompt = emptyMap(),
            beginPrompt = emptyMap(),
            continuePrompt = emptyMap(),
            endPrompt = emptyMap(),
            numberOfCycle = 0
        )

        fun combineInstructions(
            language: String,
            prepPrompt: Map<String, Map<String, Long>>,
            beginPrompt: Map<String, Map<String, Long>>,
            continuePrompt: Map<String, Map<String, Long>>,
            endPrompt: Map<String, Map<String, Long>>,
            cycles: Int
        ): List<TimedInstruction> {
            val instructions = mutableListOf<TimedInstruction>()

            prepPrompt[language]?.forEach { (text, timing) ->
                instructions.add(TimedInstruction(text, timing, BreathType.NEUTRAL))
            }

            beginPrompt[language]?.forEach { (text, timing) ->
                val type = detectBreathPhase(text).first
                instructions.add(TimedInstruction(text, timing, type))
            }

            repeat(cycles) {
                continuePrompt[language]?.forEach { (text, timing) ->
                    val type = detectBreathPhase(text).first
                    instructions.add(TimedInstruction(text, timing, type))
                }
            }

            endPrompt[language]?.forEach { (text, timing) ->
                instructions.add(TimedInstruction(text, timing, BreathType.NEUTRAL))
            }

            return instructions
        }

        fun detectBreathPhase(text: String): Pair<BreathType, Float> {
            return when {
                // English patterns
                text.contains(Regex("inhale|breathe in|श्वास अंदर|श्वास लें", RegexOption.IGNORE_CASE)) ->
                    Pair(BreathType.INHALE, 1f)

                text.contains(Regex("exhale|breathe out|श्वास बाहर|श्वास छोड़े|श्वास बाहर छोड़ें", RegexOption.IGNORE_CASE)) ->
                    Pair(BreathType.EXHALE, 1f)

                text.contains(Regex("hold|retain|रोकें", RegexOption.IGNORE_CASE)) ->
                    Pair(BreathType.HOLD, 1f)

                text.contains(Regex("sound|ocean|hiss|ध्वनि", RegexOption.IGNORE_CASE)) ->
                    Pair(BreathType.SOUND, 0.8f)

                // Special cases (Bhastrika, Kapalbhati)
                text.contains(Regex("forceful|rapid|तेज़", RegexOption.IGNORE_CASE)) ->
                    Pair(BreathType.EXHALE, 0.6f)

                else -> Pair(BreathType.NEUTRAL, 1f)
            }
        }
    }
}