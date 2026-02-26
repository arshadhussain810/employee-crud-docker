document.addEventListener("DOMContentLoaded", function () {
    const success = document.getElementById("successFlag")?.value;
    const message = document.getElementById("successMessage")?.value;

    if(success === "true"){
        const popup = document.getElementById("successPopup");
        const popupMessage = document.getElementById("popupMessage");

        popupMessage.textContent = message || "save successfully"
        popup.classList.remove("hidden")
    }
});
function closePopup() {
    document.getElementById("successPopup").classList.add("hidden");
}