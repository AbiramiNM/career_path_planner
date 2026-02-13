function buildScaleControls() {
    const labels = [
        { value: 1, text: "1", caption: "Strongly Disagree" },
        { value: 2, text: "2", caption: "Disagree" },
        { value: 3, text: "3", caption: "Neutral" },
        { value: 4, text: "4", caption: "Agree" },
        { value: 5, text: "5", caption: "Strongly Agree" }
    ];

    document.querySelectorAll(".scale").forEach(scale => {
        const field = scale.getAttribute("data-field");
        labels.forEach(label => {
            const btn = document.createElement("button");
            btn.type = "button";
            btn.className = "scale-button";
            btn.dataset.value = label.value;
            btn.dataset.field = field;
            btn.innerHTML = `${label.text}<span>${label.caption}</span>`;
            btn.addEventListener("click", () => selectScaleValue(field, label.value));
            scale.appendChild(btn);
        });
    });
}

function selectScaleValue(field, value) {
    document
        .querySelectorAll(`.scale[data-field="${field}"] .scale-button`)
        .forEach(btn => {
            btn.classList.toggle("selected", Number(btn.dataset.value) === value);
        });
}

function getFormData() {
    const errorElement = document.getElementById("error-message");
    errorElement.hidden = true;
    errorElement.textContent = "";

    const selectedValues = {};
    let missingFields = [];

    document.querySelectorAll(".scale").forEach(scale => {
        const field = scale.getAttribute("data-field");
        const selected = scale.querySelector(".scale-button.selected");
        if (!selected) {
            missingFields.push(field);
        } else {
            selectedValues[field] = Number(selected.dataset.value);
        }
    });

    if (missingFields.length > 0) {
        errorElement.hidden = false;
        errorElement.textContent = "Please answer all the interest questions before submitting.";
        return null;
    }

    return {
        name: document.getElementById("name").value || null,
        email: document.getElementById("email").value || null,
        ...selectedValues
    };
}

async function submitForm(event) {
    event.preventDefault();

    const payload = getFormData();
    if (!payload) {
        return;
    }

    const submitButton = document.querySelector(".primary-btn");
    submitButton.disabled = true;
    submitButton.textContent = "Calculating...";

    try {
        const response = await fetch("/api/recommend", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(payload)
        });

        if (!response.ok) {
            throw new Error("Server returned an error");
        }

        const data = await response.json();
        renderResult(data);
    } catch (e) {
        const errorElement = document.getElementById("error-message");
        errorElement.hidden = false;
        errorElement.textContent = "Unable to contact the server. Please make sure the backend is running and try again.";
    } finally {
        submitButton.disabled = false;
        submitButton.textContent = "Find My Career Domain";
    }
}

function renderResult(data) {
    const card = document.getElementById("result-card");
    const container = document.getElementById("result-content");

    container.innerHTML = "";

    const primary = document.createElement("div");
    primary.className = "result-domain";
    primary.textContent = data.primaryDomain;
    container.appendChild(primary);

    if (data.otherSuitableDomains && data.otherSuitableDomains.length > 0) {
        const title = document.createElement("div");
        title.className = "result-section-title";
        title.textContent = "Other suitable domains for you:";
        container.appendChild(title);

        const pillContainer = document.createElement("div");
        pillContainer.className = "pill-list";
        data.otherSuitableDomains.forEach(domain => {
            const pill = document.createElement("span");
            pill.className = "pill";
            pill.textContent = domain;
            pillContainer.appendChild(pill);
        });
        container.appendChild(pillContainer);
    }

    if (data.explanation) {
        const explanationTitle = document.createElement("div");
        explanationTitle.className = "result-section-title";
        explanationTitle.textContent = "Why this domain?";
        container.appendChild(explanationTitle);

        const explanation = document.createElement("p");
        explanation.className = "result-text";
        explanation.textContent = data.explanation;
        container.appendChild(explanation);
    }

    if (data.suggestedCareers && data.suggestedCareers.length > 0) {
        const careersTitle = document.createElement("div");
        careersTitle.className = "result-section-title";
        careersTitle.textContent = "Example careers:";
        container.appendChild(careersTitle);

        const pills = document.createElement("div");
        pills.className = "pill-list";
        data.suggestedCareers.forEach(c => {
            const pill = document.createElement("span");
            pill.className = "pill";
            pill.textContent = c;
            pills.appendChild(pill);
        });
        container.appendChild(pills);
    }

    if (data.suggestedNextSteps && data.suggestedNextSteps.length > 0) {
        const stepsTitle = document.createElement("div");
        stepsTitle.className = "result-section-title";
        stepsTitle.textContent = "Suggested next steps:";
        container.appendChild(stepsTitle);

        const list = document.createElement("ul");
        list.className = "step-list";
        data.suggestedNextSteps.forEach(step => {
            const li = document.createElement("li");
            li.textContent = step;
            list.appendChild(li);
        });
        container.appendChild(list);
    }

    card.hidden = false;
}

function resetForm() {
    document.querySelectorAll(".scale-button.selected").forEach(btn => {
        btn.classList.remove("selected");
    });

    const card = document.getElementById("result-card");
    const container = document.getElementById("result-content");
    container.innerHTML = "";
    card.hidden = true;

    const errorElement = document.getElementById("error-message");
    errorElement.hidden = true;
    errorElement.textContent = "";
}

document.addEventListener("DOMContentLoaded", () => {
    buildScaleControls();
    document.getElementById("assessment-form").addEventListener("submit", submitForm);
    document.getElementById("assessment-form").addEventListener("reset", resetForm);
});

