function countCharacters(input, counterId, maxLength) {
    var currentLength = input.value.length;
    var remainingLength = maxLength - currentLength;
    document.getElementById(counterId).innerHTML = remainingLength + " caracteres restantes";
    if (remainingLength <= 0) {
        input.value = input.value.substring(0, maxLength);
    }
}