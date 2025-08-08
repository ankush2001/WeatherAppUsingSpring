async function getWeather() {
    const city = document.getElementById('cityInput').value;
    if (!city) {
        alert('Please enter a city name');
        return;
    }

    try {
        const response = await fetch(`/api/weather?city=${city}`);
        if (!response.ok) {
            throw new Error('City not found');
        }
        const data = await response.json();

        document.getElementById('weatherResult').innerHTML = `
                    <strong>City:</strong> ${data.city}, ${data.country} <br>
                    <strong>Description:</strong> ${data.description} <br>
                    <strong>Temperature:</strong> ${data.temperature}°C <br>
                    <strong>Humidity:</strong> ${data.humidity}% <br>
                    <strong>Wind Speed:</strong> ${data.windSpeed} m/s
                `;
    } catch (error) {
        document.getElementById('weatherResult').innerHTML = `<span style="color:red;">${error.message}</span>`;
    }
}