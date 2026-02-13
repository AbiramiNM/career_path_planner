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

let lastRecommendation = { primaryDomain: null, otherSuitableDomains: [] };

function renderResult(data) {
    const card = document.getElementById("result-card");
    const container = document.getElementById("result-content");
    const actionsEl = document.getElementById("result-actions");
    const detailBlock = document.getElementById("domain-detail-block");
    const compareCard = document.getElementById("compare-card");

    container.innerHTML = "";
    actionsEl.innerHTML = "";
    detailBlock.innerHTML = "";
    detailBlock.hidden = true;
    document.getElementById("compare-result").innerHTML = "";
    document.getElementById("compare-result").hidden = true;

    lastRecommendation = {
        primaryDomain: data.primaryDomain,
        otherSuitableDomains: data.otherSuitableDomains || []
    };

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

    if (data.primaryDomain && data.primaryDomain !== "Exploration Needed") {
        const viewDetailsBtn = document.createElement("button");
        viewDetailsBtn.type = "button";
        viewDetailsBtn.className = "primary-btn";
        viewDetailsBtn.textContent = "View domain details (skills, roadmap, certifications)";
        viewDetailsBtn.addEventListener("click", loadAndShowDomainDetails);
        actionsEl.appendChild(viewDetailsBtn);
    }

    const allDomains = [data.primaryDomain].concat(data.otherSuitableDomains || []).filter(d => d && d !== "Exploration Needed");
    if (allDomains.length >= 2) {
        compareCard.hidden = false;
        const sel1 = document.getElementById("compare-domain1");
        const sel2 = document.getElementById("compare-domain2");
        sel1.innerHTML = "<option value=\"\">Select domain</option>" + allDomains.map(d => "<option value=\"" + escapeHtml(d) + "\">" + escapeHtml(d) + "</option>").join("");
        sel2.innerHTML = "<option value=\"\">Select domain</option>" + allDomains.map(d => "<option value=\"" + escapeHtml(d) + "\">" + escapeHtml(d) + "</option>").join("");
    } else {
        compareCard.hidden = true;
    }

    card.hidden = false;
}

function escapeHtml(s) {
    const div = document.createElement("div");
    div.textContent = s;
    return div.innerHTML;
}

async function loadAndShowDomainDetails() {
    const domainName = lastRecommendation.primaryDomain;
    if (!domainName || domainName === "Exploration Needed") return;

    const block = document.getElementById("domain-detail-block");
    block.innerHTML = "<p class=\"result-text\">Loading...</p>";
    block.hidden = false;

    try {
        const res = await fetch("/api/domains/details?name=" + encodeURIComponent(domainName));
        if (!res.ok) {
            block.innerHTML = "<p class=\"error-message\">Could not load domain details.</p>";
            return;
        }
        const data = await res.json();
        renderDomainDetails(data, block);
    } catch (e) {
        block.innerHTML = "<p class=\"error-message\">Unable to load details. Check if the server is running.</p>";
    }
}

function renderDomainDetails(data, block) {
    block.innerHTML = "";

    if (data.description) {
        const desc = document.createElement("p");
        desc.className = "result-text";
        desc.textContent = data.description;
        block.appendChild(desc);
    }

    if (data.coreSkills && data.coreSkills.length > 0) {
        const title = document.createElement("div");
        title.className = "result-section-title";
        title.textContent = "Required core skills";
        block.appendChild(title);
        const ul = document.createElement("ul");
        ul.className = "step-list";
        data.coreSkills.forEach(s => {
            const li = document.createElement("li");
            li.textContent = s;
            ul.appendChild(li);
        });
        block.appendChild(ul);
    }

    if (data.roadmap && data.roadmap.length > 0) {
        const title = document.createElement("div");
        title.className = "result-section-title";
        title.textContent = "Beginner learning roadmap";
        block.appendChild(title);
        const section = document.createElement("div");
        section.className = "detail-section";
        data.roadmap.sort((a, b) => (a.stepOrder || 0) - (b.stepOrder || 0));
        data.roadmap.forEach(step => {
            const div = document.createElement("div");
            div.className = "roadmap-step";
            div.innerHTML = "<strong>Step " + (step.stepOrder) + ": " + escapeHtml(step.title || "") + "</strong><p>" + escapeHtml(step.description || "") + "</p>";
            section.appendChild(div);
        });
        block.appendChild(section);
    }

    if (data.certifications && data.certifications.length > 0) {
        const title = document.createElement("div");
        title.className = "result-section-title";
        title.textContent = "Suggested certifications / learning areas";
        block.appendChild(title);
        data.certifications.forEach(c => {
            const div = document.createElement("div");
            div.className = "cert-item";
            div.innerHTML = "<strong>" + escapeHtml(c.name || "") + "</strong>" + (c.level ? " <span class=\"level\">(" + escapeHtml(c.level) + ")</span>" : "") + "<br>" + escapeHtml(c.description || "");
            block.appendChild(div);
        });
    }
}

document.getElementById("compare-btn").addEventListener("click", async function () {
    const d1 = document.getElementById("compare-domain1").value;
    const d2 = document.getElementById("compare-domain2").value;
    const resultEl = document.getElementById("compare-result");

    if (!d1 || !d2) {
        resultEl.innerHTML = "<p class=\"error-message\">Please select both domains.</p>";
        resultEl.hidden = false;
        return;
    }
    if (d1 === d2) {
        resultEl.innerHTML = "<p class=\"error-message\">Please select two different domains.</p>";
        resultEl.hidden = false;
        return;
    }

    resultEl.innerHTML = "<p class=\"result-text\">Loading comparison...</p>";
    resultEl.hidden = false;

    try {
        const res = await fetch("/api/domains/compare?domain1=" + encodeURIComponent(d1) + "&domain2=" + encodeURIComponent(d2));
        if (!res.ok) {
            resultEl.innerHTML = "<p class=\"error-message\">Could not load comparison.</p>";
            return;
        }
        const data = await res.json();
        renderComparison(data, resultEl);
    } catch (e) {
        resultEl.innerHTML = "<p class=\"error-message\">Unable to load comparison.</p>";
    }
});

function renderComparison(data, resultEl) {
    const d1 = data.domain1 || {};
    const d2 = data.domain2 || {};
    const skills1 = (d1.requiredSkills || []).length ? "<ul><li>" + (d1.requiredSkills || []).map(s => escapeHtml(s)).join("</li><li>") + "</li></ul>" : "—";
    const skills2 = (d2.requiredSkills || []).length ? "<ul><li>" + (d2.requiredSkills || []).map(s => escapeHtml(s)).join("</li><li>") + "</li></ul>" : "—";

    resultEl.innerHTML =
        "<table class=\"compare-table\">" +
        "<thead><tr><th>Criteria</th><th>" + escapeHtml(data.domain1Name || "") + "</th><th>" + escapeHtml(data.domain2Name || "") + "</th></tr></thead>" +
        "<tbody>" +
        "<tr><th>Required skills</th><td>" + skills1 + "</td><td>" + skills2 + "</td></tr>" +
        "<tr><th>Work style</th><td>" + escapeHtml(d1.workStyle || "—") + "</td><td>" + escapeHtml(d2.workStyle || "—") + "</td></tr>" +
        "<tr><th>Industry exposure</th><td>" + escapeHtml(d1.industryExposure || "—") + "</td><td>" + escapeHtml(d2.industryExposure || "—") + "</td></tr>" +
        "<tr><th>Growth opportunities</th><td>" + escapeHtml(d1.growthOpportunities || "—") + "</td><td>" + escapeHtml(d2.growthOpportunities || "—") + "</td></tr>" +
        "</tbody></table>";
}

function resetForm() {
    document.querySelectorAll(".scale-button.selected").forEach(btn => {
        btn.classList.remove("selected");
    });

    const card = document.getElementById("result-card");
    const container = document.getElementById("result-content");
    container.innerHTML = "";
    card.hidden = true;

    document.getElementById("result-actions").innerHTML = "";
    document.getElementById("domain-detail-block").innerHTML = "";
    document.getElementById("domain-detail-block").hidden = true;

    document.getElementById("compare-card").hidden = true;
    document.getElementById("compare-result").innerHTML = "";
    document.getElementById("compare-result").hidden = true;

    lastRecommendation = { primaryDomain: null, otherSuitableDomains: [] };

    const errorElement = document.getElementById("error-message");
    errorElement.hidden = true;
    errorElement.textContent = "";
}

document.addEventListener("DOMContentLoaded", () => {
    buildScaleControls();
    document.getElementById("assessment-form").addEventListener("submit", submitForm);
    document.getElementById("assessment-form").addEventListener("reset", resetForm);
});

