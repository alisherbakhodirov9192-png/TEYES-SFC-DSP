# Carrozzeria SFC DSP — Universal TEYES Edition (Android Studio source)

## Universal model support
UI app targets Android API 23+ and displays the detected device manufacturer/model/API at runtime. This makes the same base APK installable on many TEYES Android head units, subject to their Android version and ABI.

Important: universal installation is NOT the same as universal hardware DSP control. TEYES models use different audio architectures/chips and firmware interfaces. Without a documented TEYES DSP service/API for a specific model, this app cannot guarantee control of the built-in hardware DSP or all system audio sources.

## Nimalar bor
- Original FH-P009MD SFC rejimlari: OFF, STUDIO, CLUB, HALL (CONCERT HALL), DOME.
- SFC va BBE-style effektlari o'zaro bloklanadi (original qurilmadagi kabi).
- 13-band EQ boshqaruv paneli.
- Reverb, stereo width va output gain.
- `DSPProcessor.java`: stereo float PCM oqimi uchun DSP yadrosi.

## Muhim cheklov
Bu 0.1 prototip: UI va PCM DSP yadrosi mavjud, ammo audio manba/player hali ulanmagan. Android ilovasi odatda YouTube, Spotify, radio kabi boshqa ilovalarning audio oqimini erkin ushlab qayta ishlay olmaydi. Butun tizim ovoziga ta'sir qilish uchun magnitola ishlab chiqaruvchisining DSP/audio HAL API yoki maxsus firmware/root integratsiyasi kerak bo'lishi mumkin.

DSP yadrosini ishlatish uchun audio player `float[] left, float[] right` PCM bloklarini `DSPProcessor.process(left, right)` ga yuborishi kerak. Hozirgi SFC algoritmlari original Pioneer firmware nusxasi emas, balki xona aks-sadosi va stereo ishlov berishning taxminiy DSP emulyatsiyasidir. BBE-style ham original BBE algoritmi emas.

## Ochish
Android Studio -> Open -> ushbu papkani tanlang. Gradle sync qiling va Android magnitolada ishga tushiring.
