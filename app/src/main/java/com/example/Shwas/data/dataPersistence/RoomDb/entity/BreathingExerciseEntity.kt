package com.example.Shwas.data.dataPersistence.RoomDb.entity

import androidx.compose.ui.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breathing_exercise_entity")
data class BreathingExerciseEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "name") val nameTranslations: Map<String, String>,
    @ColumnInfo(name = "description") val descriptionTranslations: Map<String, String>,
    @ColumnInfo(name = "instruction") val instructionTranslations: Map<String, String>,

    // Non-translatable fields
    @ColumnInfo(name = "duration") val durationMinutes: Int,
    @ColumnInfo(name = "difficulty") val difficulty: String,
    @ColumnInfo(name = "card_color") val cardColor: Int,
    @ColumnInfo(name = "text_color") val textColor: Int,
    @ColumnInfo(name = "accent_color") val accentColor: Int,
    @ColumnInfo(name = "is_favourite") val isFavorite: Boolean = false,
    @ColumnInfo(name = "is_expanded") val isExpanded: Boolean = false
) {
    companion object {
        val breathingExercises = listOf(
            BreathingExerciseEntity(
                id = "Brahmari",
                nameTranslations = mapOf(
                    "en" to "Humming Breath",
                    "hi" to "भ्रामरी प्राणायाम"
                ),
                descriptionTranslations = mapOf(
                    "en" to "Creates a resonant vibration in the head and chest. Calms vata and enhances mental clarity.",
                    "hi" to "सिर और हृदय में कंपन उत्पन्न करता है। वात को संतुलित करता है और मानसिक स्पष्टता बढ़ाता है।"
                ),
                instructionTranslations = mapOf(
                    "en" to "Inhale like Ujjayi, exhale with a humming bee sound. Optionally, close ears during exhale for deeper resonance.",
                    "hi" to "उज्जायी की तरह श्वास लें और मधुमक्खी जैसी ध्वनि के साथ श्वास छोड़ें। चाहें तो कान बंद करके अभ्यास करें।"
                ),
                durationMinutes = 6,
                difficulty = "Beginner",
                cardColor = 0xFFFFF3E0.toInt(),
                textColor = 0xFF8D6E63.toInt(),
                accentColor = 0xFFfce397.toInt()
            ),

            BreathingExerciseEntity(
                id = "Surya Bhedana",
                nameTranslations = mapOf(
                    "en" to "Solar Breath",
                    "hi" to "सूर्य भेदा प्राणायाम"
                ),
                descriptionTranslations = mapOf(
                    "en" to "A heating breath balancing vata. Avoid in heart disease, hypertension, and similar conditions.",
                    "hi" to "एक गर्म प्रभाव देने वाली श्वास तकनीक जो वात को संतुलित करती है। हृदय रोग, उच्च रक्तचाप में न करें।"
                ),
                instructionTranslations = mapOf(
                    "en" to "Inhale through right nostril, exhale through left. Practice for 6 breaths to 10 minutes.",
                    "hi" to "दाहिनी नासिका से श्वास लें, बाईं से छोड़ें। 6 श्वासों से 10 मिनट तक अभ्यास करें।"
                ),
                durationMinutes = 6,
                difficulty = "Intermediate",
                cardColor = 0xFFFFF8E1.toInt(),
                textColor = 0xFFFFA000.toInt(),
                accentColor = 0xFFFFE082.toInt()
            ),

            BreathingExerciseEntity(
                id = "Siitkari Kumbhaka",
                nameTranslations = mapOf(
                    "en" to "Hissing Breath",
                    "hi" to "सीतकारी कुम्भक"
                ),
                descriptionTranslations = mapOf(
                    "en" to "Same benefits as Shiitali; reduces heat and purifies the senses. Avoid during asthma, bronchitis, or constipation.",
                    "hi" to "शीतली जैसे ही लाभ देता है; शरीर की गर्मी कम करता है और इंद्रियों को शुद्ध करता है। अस्थमा, ब्रोंकाइटिस में न करें।"
                ),
                instructionTranslations = mapOf(
                    "en" to "Inhale through nose, hold for 8 seconds, exhale through mouth making ‘s-s-s’ sound with tongue behind teeth.",
                    "hi" to "नाक से श्वास लें, 8 सेकंड तक रोकें, मुँह से 'स-स-स' ध्वनि निकालते हुए छोड़ें।"
                ),
                durationMinutes = 4,
                difficulty = "Intermediate",
                cardColor = 0xFFF1F8E9.toInt(),
                textColor = 0xFF558B2F.toInt(),
                accentColor = 0xFFAED581.toInt()
            ),

            BreathingExerciseEntity(
                id = "Shiitali Kumbhaka",
                nameTranslations = mapOf(
                    "en" to "Cooling Breath",
                    "hi" to "शीतली कुम्भक"
                ),
                descriptionTranslations = mapOf(
                    "en" to "Reduces heat and calms the upper digestive and nervous system. Should be avoided in asthma, bronchitis, or constipation.",
                    "hi" to "शरीर की गर्मी को कम करता है और पाचन तथा तंत्रिका तंत्र को शांत करता है। अस्थमा, ब्रोंकाइटिस या कब्ज में न करें।"
                ),
                instructionTranslations = mapOf(
                    "en" to "Fold tongue lengthwise, inhale through it, close mouth, hold for 8 counts, then exhale through nose. Do up to 8 breaths.",
                    "hi" to "जीभ को लंबाई में मोड़कर श्वास लें, मुँह बंद करें, 8 गिनती तक रोकें और फिर नाक से छोड़ें। अधिकतम 8 बार दोहराएं।"
                ),
                durationMinutes = 4,
                difficulty = "Intermediate",
                cardColor = 0xFFE8F5E9.toInt(),
                textColor = 0xFF3E6C58.toInt(),
                accentColor = 0xFFC8E6C9.toInt()
            ),

            BreathingExerciseEntity(
                id = "Ujjayi",
                nameTranslations = mapOf(
                    "en" to "Ocean’s Breath",
                    "hi" to "उज्जायी प्राणायाम"
                ),
                descriptionTranslations = mapOf(
                    "en" to "A heating and calming breath that sounds like ocean waves.",
                    "hi" to "एक गर्माहट देने वाली और मन को शांत करने वाली श्वास प्रक्रिया जो समुद्र की लहरों जैसी प्रतीत होती है।"
                ),
                instructionTranslations = mapOf(
                    "en" to "Inhale deeply through the nose, constricting the throat slightly to create a sound. Exhale slowly with the same constriction. Repeat for 5 minutes.",
                    "hi" to "नाक से गहरी श्वास लें और गले को हल्का संकुचित करते हुए ध्वनि उत्पन्न करें। धीरे-धीरे उसी संकुचन के साथ श्वास छोड़ें। 5 मिनट तक दोहराएं।"
                ),
                durationMinutes = 5,
                difficulty = "Intermediate",
                cardColor = 0xFFF0F7FF.toInt(),
                textColor = 0xFF4A6572.toInt(),
                accentColor = 0xFFB8D8EB.toInt()
            ),

            BreathingExerciseEntity(
                id = "Nadi Shodhana",
                nameTranslations = mapOf(
                    "en" to "Alternate Nostril Breathing",
                    "hi" to "अनुलोम विलोम प्राणायाम"
                ),
                descriptionTranslations = mapOf(
                    "en" to "A calming technique alternating air between nostrils. Helps with anxiety and balance.",
                    "hi" to "एक शांत करने वाली विधि जिसमें बारी-बारी से नासिका से श्वास लिया और छोड़ा जाता है। यह चिंता और संतुलन में मदद करती है।"
                ),
                instructionTranslations = mapOf(
                    "en" to "Inhale through left nostril while closing right. Exhale through right, then inhale right and exhale left. Repeat for 3–5 minutes.",
                    "hi" to "दाहिनी नासिका बंद कर बाईं से श्वास लें। फिर दाहिनी से श्वास छोड़ें। फिर दाहिनी से लें और बाईं से छोड़ें। 3–5 मिनट तक दोहराएं।"
                ),
                durationMinutes = 5,
                difficulty = "Beginner",
                cardColor = 0xFFE6F2FF.toInt(),
                textColor = 0xFF434E5C.toInt(),
                accentColor = 0xFFA8C4E0.toInt()
            ),

            BreathingExerciseEntity(
                id = "Active Yogic Breathing",
                nameTranslations = mapOf(
                    "en" to "Active Yogic Breathing",
                    "hi" to "सक्रिय योगिक श्वास"
                ),
                descriptionTranslations = mapOf(
                    "en" to "A walking breath practice combining movement with extended breathing patterns.",
                    "hi" to "चलते समय गहरी और लंबी श्वास लेने की प्रक्रिया जो शरीर और मन को एक साथ जोड़ती है।"
                ),
                instructionTranslations = mapOf(
                    "en" to "While walking slowly, inhale and exhale deeply through nose, counting steps per breath. Aim for 10 steps or more.",
                    "hi" to "धीरे चलते हुए नाक से लंबी श्वास लें और छोड़ें, हर श्वास के लिए कदम गिनें। 10 कदम या अधिक लक्ष्य रखें।"
                ),
                durationMinutes = 10,
                difficulty = "Beginner",
                cardColor = 0xFFE3F2FD.toInt(),
                textColor = 0xFF1E3A5F.toInt(),
                accentColor = 0xFF90CAF9.toInt()
            ),

            BreathingExerciseEntity(
                id = "Bhastrika",
                nameTranslations = mapOf(
                    "en" to "Bellows Breath",
                    "hi" to "भस्त्रिका प्राणायाम"
                ),
                descriptionTranslations = mapOf(
                    "en" to "A vigorous breathing technique that energizes and clears physical, mental, and emotional blocks.",
                    "hi" to "तेज़ श्वास-प्रश्वास द्वारा शरीर और मन को ऊर्जा देने वाला अभ्यास।"
                ),
                instructionTranslations = mapOf(
                    "en" to "Inhale 20 rapid breaths through one nostril, then the other, then both. Only do under supervision.",
                    "hi" to "एक नासिका से 20 तेज श्वास लें, फिर दूसरी से, फिर दोनों से। केवल शिक्षक की देखरेख में करें।"
                ),
                durationMinutes = 3,
                difficulty = "Advanced",
                cardColor = 0xFFFFEBEE.toInt(),
                textColor = 0xFF6D4C41.toInt(),
                accentColor = 0xFFFFCDD2.toInt()
            ),

            BreathingExerciseEntity(
                id = "Chandra Bhedana",
                nameTranslations = mapOf(
                    "en" to "Lunar Breath",
                    "hi" to "चन्द्र भेदा प्राणायाम"
                ),
                descriptionTranslations = mapOf(
                    "en" to "A cooling breath that reduces heat and soothes the mind. Avoid in depression or mucus issues.",
                    "hi" to "शरीर की गर्मी कम करने और मन को शांत करने वाली तकनीक। अवसाद या कफ में न करें।"
                ),
                instructionTranslations = mapOf(
                    "en" to "Inhale through left nostril, exhale through right. Do 6 breaths to a maximum of 10 minutes.",
                    "hi" to "बाईं नासिका से श्वास लें, दाहिनी से छोड़ें। 6 श्वासों से 10 मिनट तक करें।"
                ),
                durationMinutes = 6,
                difficulty = "Intermediate",
                cardColor = 0xFFF3E5F5.toInt(),
                textColor = 0xFF6A1B9A.toInt(),
                accentColor = 0xFFD1C4E9.toInt()
            )
        )
    }
}