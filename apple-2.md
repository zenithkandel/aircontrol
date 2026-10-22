---
name: coded-cinematic-product-film-art-direction
version: 1.0.0
last_researched: 2026-10-18
description: A research-grounded art-direction, motion-design, and engineering skill for creating original, premium 3D product advertisements and brand films with HTML, CSS, JavaScript, WebGL, Three.js, GSAP, and deterministic code-driven rendering. Uses selected Apple campaigns as a quality reference without treating Apple as a single style or copying its protected identity.
---

# Coded Cinematic Product Films — Art Direction & Engineering Skill

## Mission

Create **original, high-craft, cinematic product advertisements entirely through code**. Use HTML, CSS, JavaScript, WebGL, Three.js, GSAP, shaders, procedural geometry, SVG, Canvas, and other appropriate code-based tools. The target is the intentionality, polish, rhythm, material credibility, clarity, and emotional precision associated with the strongest Apple product films and keynote sequences—not a superficial imitation of Apple's logo, layouts, colors, typefaces, sounds, or individual ads.

This skill is for **video and motion graphics**, not for designing website UI. A browser may be the rendering environment, but the deliverable is a film: a sequence of designed shots with a beginning, development, reveal, and satisfying finish. Do not turn the result into a landing page, dashboard, product configurator, or floating pile of UI cards unless the brief explicitly calls for one.

The goal is not to reproduce every possible Apple animation. No public source documents every proprietary studio decision or every frame of every campaign. Instead, apply a rigorous process to publicly documented design principles, public ads and keynote films, reputable production breakdowns, and established graphics-engineering documentation.

**Capability boundary:** HTML/CSS/JavaScript/WebGL can produce sophisticated and impressive films, but code alone does not guarantee the same finish as a major studio pipeline. The strongest commercial work may combine accurate CAD, custom shaders, carefully authored assets, practical photography, offline rendering, color-managed finishing, professional sound, and extensive human review. Use code to deliver the strongest result possible within the available assets, browser, GPU, render time, and output constraints. Do not claim parity with a production pipeline that the project does not actually have.

## Core operating rule

**The idea leads. The product is the protagonist. Every frame earns its place. Motion must clarify, reveal, or make the product feel desirable.**

Never optimize for the number of effects. Optimize for the strength of the few effects that matter.

## What “Apple-level” means in this skill

Treat “Apple-level” as a benchmark for craft, not as a guarantee or a literal visual preset. Translate the benchmark into observable qualities:

- One memorable central idea, communicated visually before it is explained.
- A product whose shape, material, construction, and most important feature are easy to read.
- Art direction that appears chosen, not generated from a stack of presets.
- Lighting that reveals form and material instead of merely making everything glow.
- Camera paths with a clear motivation, controlled acceleration, and elegant stopping points.
- Transitions that grow from the objects, rhythm, sound, or idea rather than appearing as unrelated tricks.
- Typography with a deliberate hierarchy, careful spacing, and very little copy.
- Color that is specific to the product and concept rather than defaulting to trendy gradients.
- Sound designed in sync with the physical and visual action.
- A finish that feels complete: the product resolves into a memorable final image instead of abruptly stopping.
- Technical reliability: the sequence works, renders consistently, and contains no visible artifacts or console errors.

Apple’s public Human Interface Guidelines are written for interactive software, not for advertising films. Their principles—purposeful motion, craft, clarity, meaningful color, and simplicity—are useful starting points, but this skill **adapts** them for cinematic work rather than treating app-animation durations or rules as advertising standards. See the source register at the end.

---

# 1. First principles: think like a director, not like a code generator

## 1.1 Begin with the human meaning

Before writing code, answer these internally:

1. **What is the one thing the audience must remember?** Pick one feature, feeling, use, material, engineering achievement, or brand belief.
2. **What should the audience feel?** Possibilities include precision, relief, wonder, confidence, playfulness, focus, speed, intimacy, or freedom. Choose one primary emotion and at most one supporting emotion.
3. **What visual event can express that feeling?** A thin edge catching a moving light can communicate precision. A heavy object moving effortlessly can communicate power. A repeated shape snapping into alignment can communicate engineered order. A small sound opening into a vast spatial reveal can communicate immersion.
4. **What is the simplest strong version of that event?** Build that version first. Add complexity only if it improves the idea.
5. **What must remain true about the product?** Establish its silhouette, dimensions, construction, materials, colors, interfaces, moving parts, and limitations.

If the idea cannot be described in one or two plain sentences, the concept is not yet ready for implementation.

## 1.2 Choose one governing visual metaphor

A strong film can contain many shots, but it usually benefits from one governing visual logic. Examples:

- **Light as measurement:** grazing highlights reveal a precision-machined edge.
- **Controlled tension:** separated pieces hover in exact balance, then resolve into a complete object.
- **Material discovery:** the camera enters a surface, reveals its structure, and returns to the finished product.
- **Effortless power:** an object accelerates with intent, then stops so cleanly that the audience feels its control.
- **Sound made visible:** a physical event creates a precisely designed wave, vibration, or environmental response.
- **One shape, many scales:** a macro detail transitions into the complete product through a geometrically believable match.
- **Human expression:** a person, gesture, or crafted object makes the product's benefit emotionally legible.

Do not combine five unrelated metaphors in one 20-second spot. If a second idea appears, it should deepen the first.

## 1.3 Write a visual thesis before a shot list

Use this template:

> This film makes **[audience]** feel **[emotion]** about **[product]** by showing **[one visual idea]**. It begins with **[hook]**, develops through **[revelation]**, proves **[product truth]**, and ends on **[final image / message]**.

Then write a one-line anti-thesis: “This is not a film about …” The anti-thesis helps prevent common detours, such as turning a precision tool into a generic sci-fi hologram showcase.

## 1.4 Build a hierarchy of attention

At every moment there should be a clear primary subject. The hierarchy normally is:

1. Product or essential visual event.
2. The light, surface, environment, or motion that reveals it.
3. Supporting geometry, particles, atmosphere, or secondary objects.
4. Typography and brand signature, only when needed.

If the background is more visually interesting than the product, redesign the background. If the title wins attention before the object is introduced, adjust the title timing or composition. If all layers are equally intense, none is important.

## 1.5 Silence and stillness are design materials

A moment with very little movement is not a gap that must be filled. It creates anticipation, gives the audience time to inspect form, and makes the next event feel more significant. Build intentional holds into the edit. A premium film often feels fast because it is **selective**, not because every frame moves quickly.

---

# 2. Research model: what to learn from Apple without misreading it

Use three evidence tiers when studying reference work.

### Tier A — documented guidance

Official Apple HIG, developer documentation, product marketing guidance, and official keynote or newsroom material can establish what Apple publicly recommends or how it describes a specific campaign. Use direct claims from these sources accurately.

### Tier B — observable creative choices

Public advertisements, keynote films, and official behind-the-scenes pieces provide evidence about what was actually made. Analyze the finished work frame by frame. Record framing, motion direction, light behavior, color, edit rhythm, typography, sound, and product visibility. Do not infer proprietary intent unless a source documents it.

### Tier C — interpretation

Statements such as “the shot feels expensive because the camera commits to one move” are useful art-direction analyses, but they are interpretations. Label them as craft principles, not as secret Apple rules.

### Important caution: Apple does not have one unchanging ad style

Apple's creative history spans multiple modes: the dystopian story of *1984*; the portrait-led, values-based *Think Different* campaign; the high-contrast dancing silhouettes of iPod advertising; the everyday comedy of *Get a Mac*; product-centric 3D and macro films; the photography-led *Shot on iPhone* work; expressive music films such as *Welcome Home* for HomePod; and more recent work built around creators, accessibility, humor, or vivid visual spectacle. These are not interchangeable looks. The best reference is the one whose **communication problem** resembles the current brief.

For each reference, ask:

- What does the first three seconds make me notice?
- How long until I understand the product or concept?
- What changes between the opening and closing image?
- What is the main source of movement: camera, product, environment, typography, subject, or edit?
- What gives the object scale and weight?
- How is visual information withheld and then revealed?
- How many colors actually dominate the image?
- How does sound change the perceived weight or speed of the action?
- What is not shown, and why might that omission help?
- Which parts are specific to this campaign and should **not** be copied into a different brand's film?

### Recommended case studies

These are analytical exercises, not templates to reproduce shot for shot.

**A. *1984* — cinematic premise before product demonstration.** Study how setting, scale, movement, contrast, and a clear narrative conflict make the commercial feel like a short film rather than an animated feature list. Apply the lesson when a brand idea needs drama. Do not borrow its dystopian imagery or iconic staging. The [Computer History Museum's account](https://www.computerhistory.org/timeline/1984/) provides historical context.

**B. *Think Different* — brand meaning through carefully selected human images and restrained copy.** Study the relationship between image choice, pacing, negative space, voice, and a compact verbal idea. Its power comes from the emotional associations of the chosen subjects, not from adding motion effects.

**C. The iPod silhouette campaign — one graphic rule, many variations.** Study how a simple black silhouette, bright field, recognizable device, and music-driven physical movement made a campaign recognizable across many executions. The takeaway is not “use silhouettes”; it is “one strong visual rule can support a large campaign system.”

**D. *Get a Mac* — consistency through repeated staging and timing.** Study the limited set, recognizable visual roles, concise dialogue, and recurring comic structure. Repetition builds recognition when the core idea is strong. Apple's own preserved [Get a Mac campaign page](https://www.apple.com/go/awards/campaign_getamac/index.html) is a useful archive; [Macworld's contemporary analysis](https://www.macworld.com/article/179913/appleads-2.html) discusses how the format converted product differences into concise scenes.

**E. *Shot on iPhone* — proof through the output.** Apple's *One Night on iPhone 7* newsroom story documents a worldwide night-photography effort, using images captured by photographers in many locations to make the low-light camera capability tangible. The lesson for product communication is to show credible outcomes rather than relying only on a floating device and a technical claim. See [Apple's campaign account](https://www.apple.com/newsroom/2017/01/behind-apples-new-campaign-one-night-on-iphone-7/).

**F. *Welcome Home* for HomePod — choreography and world design.** The behind-the-scenes account describes practical moving sets, precise choreography, designed lighting, and comparatively little CGI. It is a useful counterexample to the idea that a premium ad must be full of digital effects: a coherent physical idea can feel more magical than a larger quantity of simulated spectacle. Read [Adweek's production breakdown](https://www.adweek.com/creativity/this-look-inside-spike-jonzes-apple-ad-is-as-fascinating-as-the-film-itself/) and [Fast Company's design account](https://www.fastcompany.com/90165359/the-ingenious-design-behind-apples-viral-homepod-ad).

**G. *Crush* for iPad Pro — a caution about metaphor.** The 2024 ad showed creative tools being crushed to represent the power of a thin iPad Pro. The reaction and Apple’s reported apology demonstrate that a technically polished film can fail if its visual metaphor appears to attack the very human values the brand wants to support. Read [Reuters' report](https://www.reuters.com/technology/apple-apologizes-ipad-pro-crush-ad-ad-age-says-2024-05-10/). Before approving an idea, ask what the image might imply to someone who does not share the intended interpretation.

**H. Product reveal/keynote films — spectacle with a job.** Browse the [Apple Events archive](https://www.apple.com/apple-events/) across product categories and years. Observe how a reveal changes the audience's understanding of a feature. Do not assume every keynote film is minimalist: some are vivid, graphic, playful, or fast. Record what the style is doing for that product.

**I. *Behind the Mac* — real creative work as product proof.** Apple's [Behind the Mac story about filmmaker Barry Jenkins](https://www.apple.com/nz/newsroom/2019/02/behind-the-mac-creator-celebrates-his-literary-icon/) connects the computer to a creative practice instead of treating the hardware as an abstract trophy. Study how a creator's real context can carry product meaning with less visual machinery.

**J. *Fuzzy Feelings* — stop-motion craft and emotional reframing.** Apple's 2023 holiday short combines stop-motion and live action to connect creative expression with empathy. Useful lessons: tactile irregularity can be intentional rather than sloppy; a product's role can be woven into a story; and an emotional turn can provide the film's payoff. See [9to5Mac's film and production roundup](https://9to5mac.com/2023/11/21/watch-apples-annual-holiday-ad-now-fuzzy-feelings-video/) and the animator's [behind-the-scenes credits on Vimeo](https://vimeo.com/888201559).

**K. Photography-led product proof.** The *Shot on iPhone* archive and Apple's creator stories show another branch of Apple's visual language: location, human subject, color, weather, and authentic image-making rather than a uniform studio set. Apple's [MAMI Select: Filmed on iPhone story](https://www.apple.com/in/newsroom/2024/04/five-filmmakers-share-their-worlds-for-mami-select-filmed-on-iphone/) is useful for studying how different filmmakers use one camera system to achieve different looks.

### Reference-study procedure

For at least three relevant films, make a shot table before designing your own:

| Timecode | Shot purpose | Subject scale | Camera move | Subject move | Lighting/material clue | Palette | Sound/event | Transition | What to learn |
|---|---|---|---|---|---|---|---|---|---|
| 00:00–00:02 | Hook | Macro / wide / silhouette | Push / orbit / lock | Reveal / still / accelerate | Edge light / broad reflection | Dominant + accent | Impact / breath / music | Cut / match / dissolve | One specific principle |

Use frame captures and timecodes if available, not memory alone. Inspect the film at full speed first, then replay individual sequences. Do not slow a film down and conclude that its native pacing is slow: playback speed can distort the perceived ease of movement.

---

# 3. Anti-AI-slop doctrine

AI slop is not defined by a single color or effect. It is the **absence of specific choices**: default visual motifs, copied templates, overused effects, arbitrary decoration, and motion with no purpose. The controls below are strict defaults, not laws that override an explicitly strong concept.

## 3.1 Default bans

Do not automatically add:

- Blue-to-purple, cyan-to-magenta, or blue-to-green gradients simply because the output is “tech.”
- A glowing blue floor, neon horizon, glowing ring, energy tunnel, holographic UI, or orbiting HUD circles without a specific concept-based reason.
- A dark void plus a random reflective floor as the universal product stage.
- Floating glass cards, pill labels, corner brackets, reticles, grid lines, fake telemetry, meaningless numerals, or tiny engineering labels as decoration.
- Particle swarms, confetti, dust, sparks, smoke, bokeh, lens flares, chromatic aberration, bloom, scan lines, glitch, light leaks, or film grain by default.
- Repeated concentric rings around a product, a tunnel of objects, or endless copies spinning around the hero object.
- A generic “cinematic” camera shake, constant camera drift, endless rotation, excessive parallax, or automatic 3D text extrusion.
- Multiple competing transitions in the same short film: whip pan, glitch, liquid morph, spin, zoom, and shatter all one after another.
- Decorative text that no viewer can read, fake specifications, invented product interfaces, nonsense technical diagrams, or numbers that imply unsupported performance.
- Placeholder content, Unicode-symbol logos, incorrect product proportions, random screws, ports, vents, button placement, or details made up to look technical.
- A gradient mesh as the only source of design, lighting, and atmosphere.
- Over-rounding everything, excessive glassmorphism, generic dashboard cards, or oversized centered headlines covering the object.
- Fake film grain that is more visible than the material, heavy vignette, crushed blacks, clipped metal highlights, and bloom halos around every edge.

If a banned motif is genuinely necessary, justify it in the shot brief: what does it communicate, what makes it specific to this product, and how will it remain subordinate to the hero? If no convincing answer exists, remove it.

## 3.2 The anti-slop substitution rule

Do not simply delete decoration and leave an empty scene. Replace the decoration with a **specific act of craft**:

| Generic shortcut | Better art-directed replacement |
|---|---|
| Random gradient background | A deliberate studio sweep, paper surface, matte color field, real environment, or shadowed architectural plane |
| Constant glow | A narrow, controlled reflection that reveals a curve or edge |
| Arbitrary particles | One physically motivated material event, or no particles |
| Spinning product | A camera move that reveals a useful feature and stops at a composed angle |
| Fake HUD labels | One accurate, legible label or a clear visual demonstration |
| Bloom to create richness | Better exposure, contrast, roughness, reflection shape, and highlight placement |
| Automatic motion blur | Motion designed around direction, acceleration, and a credible shutter interval |
| Generic cinematic whoosh | A sound with a distinct material, pitch, duration, and relation to the visible event |
| Ten fast cuts | Two or three shots whose compositions build on one another |
| Metallic object on black by default | A stage and lighting scheme chosen from the actual product story |
| “Luxury” serif everywhere | A deliberate type voice that matches the brand and film's emotional register |
| White studio background by habit | White only when it makes the product's form, color, or precision easier to understand |

## 3.3 Do not confuse minimalism with emptiness

Minimalism requires selection, alignment, contrast, and a strong focal point. An empty black scene with a tiny product can be just as generic as a cluttered one. A colorful film can be minimal if it uses one dominant visual idea, disciplined shapes, and intentional pacing. Use only what makes the intended message stronger.

## 3.4 Make a visual “specificity test”

Before rendering a full film, inspect a still frame and ask:

- Could this scene advertise any random electronics product by swapping the object? If yes, it is probably too generic.
- Is there a shape, material behavior, camera relationship, palette, or transition that belongs to this product?
- Is every glow or particle linked to a visible physical event?
- Would the frame remain interesting if every decorative overlay disappeared?
- Could the product be recognized from its silhouette alone?
- Is the composition distinct without relying on a brand logo?
- Does the frame feel intentionally designed in grayscale? If not, hue may be masking weak value structure.

---

# 4. Product truth and asset quality

## 4.1 Fidelity outranks spectacle

A cinematic camera cannot rescue a poorly modeled hero object. Product truth begins with the silhouette and continues through thickness, bevels, seams, ports, fasteners, fabric, glass, labels, interface contents, and moving parts.

When the product is a real existing design:

1. Gather reference images from multiple angles, close-ups, and scale contexts.
2. Establish a reference sheet for front, side, back, top, and important details.
3. Separate confirmed information from inferred or hidden geometry.
4. Keep real-world proportions consistent. Do not “improve” the industrial design by changing button sizes, adding vents, or inventing parts.
5. Model major forms first, then the details that catch light at final resolution.
6. Compare rendered silhouettes against references from the intended camera angles.
7. Check all visible sides, not only the hero front.
8. If exact geometry is unavailable, simplify honestly. A clean, accurate silhouette is preferable to a detailed but incorrect fake.

If an exact product model or asset is necessary but unavailable, identify the limitation and request a legitimate mesh, CAD export, GLB/glTF, or dimensioned drawing when the workflow allows it. Do not claim that guessed dimensions are verified. In an autonomous deliverable, document key assumptions in code comments or a compact asset note unless the required output contract forbids any text outside the file.

## 4.2 Geometry hierarchy

Build the mesh in this order:

1. **Silhouette:** outer dimensions and proportion.
2. **Primary volume:** body, shell, display, lens, enclosure, frame, or major mechanism.
3. **Characteristic details:** signature edge, camera island, hinge, port, grille, cutout, or cap.
4. **Material breaks:** glass-to-metal transitions, seams, gaskets, rubber, ceramic, fabric, coatings.
5. **Micro-detail:** only details that survive at the intended render size and camera distance.

Tiny detail that cannot be resolved by the delivery resolution does not improve perceived fidelity. It can instead create flicker, aliases, and noise.

## 4.3 Edge quality

Premium objects tend to reveal their form through the way light rolls across edges. Perfectly sharp 90-degree edges often look computer-generated unless the product really has them. Model believable radii and bevels at the right scale. Keep bevel widths consistent with manufacturing intent. Use normal smoothing carefully; incorrect normals cause dark seams, lumpy highlights, and the characteristic “cheap CAD render” look.

Do not add bevels everywhere indiscriminately. A gasket, glass panel, brushed surface, machined chamfer, and soft polymer edge should not share the same highlight behavior.

## 4.4 Screen content must look designed

If the product includes a screen, create actual content appropriate to the product. Avoid an empty black rectangle, random rainbow wallpaper, microscopic illegible UI, or text that resembles corrupted rendering. The screen may be a dominant source of color, but it must be part of the composition and must not overpower the hardware unless the concept specifically makes the display the hero.

Prefer a clean, high-resolution screen texture or procedural canvas. Ensure text and graphics survive final output size, perspective distortion, glare, and color grading. Use only licensed or original screen content.

## 4.5 Intellectual property and brand accuracy

Use Apple films as references for craft; do not imitate Apple's identity wholesale. Do not place Apple's logo on another brand's product, recreate a recognizable Apple campaign shot-for-shot, duplicate signature ad music, or present a speculative asset as an official Apple product. If marketing an app for Apple devices, follow Apple's current [App Store Marketing Resources and Identity Guidelines](https://developer.apple.com/app-store/marketing/guidelines/), which have specific restrictions for product imagery and 3D simulations. That guidance is not a generic ban on all 3D product advertising; its scope is Apple-device/app marketing.

---

# 5. Cinematography for 3D product films

## 5.1 The camera is an authored object

A camera move should reveal information, change emotional scale, establish weight, connect two forms, or increase anticipation. Do not move it merely because motion looks expensive.

For every move, specify:

- Starting position and aim.
- Ending position and aim.
- Main axis of travel.
- Lens / field of view.
- Speed profile, not just total duration.
- What new information is revealed during the move.
- What must remain framed and readable.
- Why the move stops where it does.

## 5.2 Shot vocabulary

Choose from this vocabulary, then adapt it to the product rather than using all of it:

**Hero wide:** the whole product, enough negative space to establish scale and shape. Often the clearest shot for a reveal or final frame.

**Three-quarter hero:** balances silhouette, depth, and visible product details. Good as a default starting point for many hard-surface products, but not mandatory.

**Orthogonal / near-orthogonal detail:** reduces distortion when shape accuracy matters. Useful for product comprehension and feature callouts.

**Macro / extreme macro:** reveals texture, machining, coating, optical construction, or a precise interaction. Macro must still provide visual orientation; random microscopic abstraction is not inherently premium.

**Grazing-light profile:** a narrow highlight travels across a surface, showing curvature and edge continuity.

**Top-down graphic shot:** creates a designed flat composition and can communicate alignment, modularity, arrangement, or symmetry.

**Low-angle hero:** creates presence and scale, but can exaggerate geometry. Check that it does not misrepresent the product.

**Orbit:** moves around a stable object to reveal its geometry. A full 360-degree orbit is rarely necessary. An incomplete, motivated arc can feel more deliberate.

**Push-in / pull-back:** changes intimacy and scale. It is strongest when the subject gives new information as the camera approaches or reveals a relationship as it withdraws.

**Lateral track:** lets reflection and parallax move across form. Especially useful for polished, brushed, or layered materials.

**Dolly plus controlled focus change:** directs attention between near and far details. Do not substitute blur for clear composition.

**Whip / rapid pan:** reserve for a clear editorial handoff or high-energy moment. A random fast camera move is not inherently dynamic.

**Locked-off shot:** grants the object visual authority and lets tiny movements or lighting changes become significant.

## 5.3 Lens and perspective

Choose field of view for the intended emotional and geometric result. A very wide lens near a product makes edges distort and can make dimensions feel inconsistent. A narrower field of view with more camera distance often creates calmer proportions and a more deliberate compression of form. Macro shots need a clear plan for depth of field and focal plane.

With Three.js, understand that `PerspectiveCamera.fov` controls the vertical field of view and that camera distance changes framing and perspective relationships. Set camera parameters deliberately; do not leave default values in place simply because the first preview looks acceptable. See the [Three.js PerspectiveCamera documentation](https://threejs.org/docs/pages/PerspectiveCamera.html).

Rules of thumb to test, not blindly apply:

- Use a moderate or longer-feeling perspective for restrained product portraits.
- Use a wider perspective only when the spatial drama helps tell the story.
- Keep a long enough hold on a detailed macro to let the eye resolve what it is seeing.
- Avoid focus breathing, focus hunting, or sudden FOV changes unless they are intentional stylistic events.
- If the product shape appears warped, first inspect camera position, field of view, and model proportions before trying to “fix” the issue in post.

## 5.4 The camera and product should not compete

Usually, choose one dominant motion source per shot:

- Camera moves while the product remains still.
- Product moves while the camera holds.
- Camera and product counter-move to reveal parallax.
- An environmental event changes around a locked product.

Complex counter-movement can be beautiful, but it requires a clear hierarchy. Avoid simultaneous camera orbit, object spin, independent tilt, scale pulse, floating bob, background rotation, and particle flow unless the sequence is intentionally chaotic.

A useful starting rule is **one primary move + one secondary detail**. For instance: a 25-degree camera arc plus a soft traveling reflection. Secondary motion should enrich the primary event, not fight it.

## 5.5 Compose every shot as a still image first

Before animating, choose a strong start frame, midpoint frame, and end frame. Each should be composed well in isolation. Then animate between them. This prevents camera movement from becoming a substitute for design.

Check:

- Product silhouette against background.
- Negative space around the hero.
- Balance of high and low contrast.
- Where the eye enters and exits the frame.
- Whether important details overlap with typography.
- Whether a cropped object looks intentional.
- Whether the composition survives a 16:9 crop, vertical crop, and square crop if multiple deliverables are required.

## 5.6 Camera easing

The camera has inertia in the viewer's perception. It should not begin and stop as if a coordinate value was toggled. Plan a velocity curve: acceleration, travel, deceleration, and settle. A cubic Bézier curve or authored GSAP `CustomEase` is usually more controllable than defaulting all shots to the same ease.

Avoid applying an ease that gives everything the same emotional personality. A camera drift, a crisp mechanical lock, a suspended object, and a fluid organic gesture should not all use an identical curve.

## 5.7 Depth of field

Depth of field can create scale and guide attention, but it should not hide errors or destroy product comprehension.

- Establish a clear focus subject and a plausible focal plane.
- Use shallow depth of field selectively; it is especially easy to make CGI look like a miniature when blur is too strong.
- Avoid blur on every shot. Some product films need exceptional edge-to-edge legibility.
- Do not blur small text until it becomes a decorative texture.
- If depth of field is simulated through post-processing, test edge artifacts and performance at final resolution.
- Keep the product's important silhouette readable even when a macro focus shift occurs.

## 5.8 Camera shake and handheld motion

Use shake only when the story calls for a physical camera, impact, subjective perspective, or documentary energy. For a precision product reveal, unnecessary shake weakens authority. If used, keep the amplitude tiny, filter high-frequency jitter, and tie the shake to a cause. Do not add noise to every camera axis at every frame.

---

# 6. Lighting: reveal form rather than decorate the scene

## 6.1 Think in reflections, not just light sources

For reflective products, what the audience sees is largely the environment reflected in the material. A metal body with a broad, shapeless environment often looks flat or black. A deliberate strip light can reveal the curve of a polished edge. A broad soft source can describe a satin surface. A controlled dark flag or negative fill can create a boundary on an otherwise bright object.

Design the reflected shapes. Ask what the material is reflecting and how that reflection moves as camera or object changes.

## 6.2 The studio-lighting toolkit

Use only the lights that solve a visual problem:

- **Key:** establishes the primary form and exposure.
- **Fill:** controls shadow density; it should not erase all contrast.
- **Rim / edge light:** separates the silhouette from a similar-value background.
- **Strip / linear reflection:** describes long edges, glass curves, or brushed metal.
- **Top softbox:** creates an organized, broad reflection across a top plane.
- **Bounce:** softens contrast where needed.
- **Negative fill / dark card:** creates a readable dark stripe in reflective surfaces.
- **Practical / emissive element:** provides a visible, motivated source such as an LED, screen, or illuminated mechanism.
- **Environment map:** supplies the surrounding reflection field for PBR materials.

Do not add every light in every scene. Begin with one main source and an environment reflection plan. Add a second light only if the material or silhouette needs it.

## 6.3 Lighting sequence

Build lighting in this order:

1. Neutral material and neutral environment.
2. A key light that reveals the primary volume.
3. A background value that separates the silhouette.
4. Controlled fill to preserve important shadow detail.
5. One or two edge / strip reflections for the characteristic surfaces.
6. Material-specific reflections and highlights.
7. Final atmospheric and post-processing decisions, if the idea needs them.

Check the scene in a neutral gray material pass. If the product's shape is still unclear, the lighting/composition has not solved the form. Check the light setup without bloom. If the shot depends on bloom to appear premium, repair the lighting first.

## 6.4 Highlight discipline

Premium highlights are shaped, placed, and protected. They should explain curvature and material, not clip into large featureless white patches. Maintain detail on important edges and glass. A bright small reflection can feel sharper than a giant overexposed region.

- Avoid pure-white clipped bands covering the product's distinctive edges.
- Do not let all metal surfaces share the same broad white reflection.
- Create contrast through bright and dark reflected areas, not just overall exposure.
- Preserve enough shadow detail to understand construction.
- Check reflections at the final angle, not only from the modeler's viewport.

## 6.5 Light movement

A moving highlight is a legitimate animation when it reveals a material or marks a beat. The reflection should travel consistently with the virtual source or camera. Avoid sliding a white stripe over the product as an arbitrary “premium sweep” disconnected from any plausible lighting. If a nonphysical sweep is a conscious graphic design effect, ensure it is visibly intentional and limited.

## 6.6 The floor and horizon

A reflective floor can ground an object, create scale, or provide a beautiful composition. It can also be the most overused shortcut in tech renders. Choose between matte, satin, polished, translucent, textured, or no floor based on the story. If the scene uses a seamless cyclorama, create an intentional horizon/gradient transition rather than an accidental gray band. Reflections should be controlled in roughness, intensity, and distortion; avoid a mirror floor just because it is easy.

---

# 7. Materials, shading, and physically credible surfaces

## 7.1 Material is more than a color

A surface is described by how it responds to light. Consider:

- Base color / albedo.
- Metalness.
- Roughness.
- Normal and micro-normal structure.
- Clearcoat.
- Transparency and transmission.
- Index-of-refraction-like behavior and thickness where supported.
- Anisotropy for brushed metal.
- Subsurface effects for materials such as silicone, wax, or skin-like surfaces, if relevant.
- Emission only for genuinely luminous materials.

Three.js `MeshStandardMaterial` and `MeshPhysicalMaterial` support physically based material workflows; the latter exposes advanced characteristics such as anisotropy, clearcoat, iridescence, and transmission. Use advanced parameters to reproduce a real material, not as a checklist. See [Three.js materials](https://threejs.org/manual/pages/materials.html) and [MeshPhysicalMaterial](https://threejs.org/docs/pages/MeshPhysicalMaterial.html).

## 7.2 Material-specific guidance

**Anodized or painted aluminum:** preserve broad color response, controlled edge highlights, and believable roughness. Too much mirror polish makes it read as chrome; too much roughness makes it read as plastic.

**Brushed metal:** use a directional roughness/normal pattern or anisotropic response at the appropriate scale. The texture should be subtle enough not to look like scratches drawn across the surface.

**Polished steel or chrome:** reflection shapes are the material. Avoid flat gray base color with an overbright rim light pretending to be metal.

**Glass:** reflections must be visible, but the object behind it should remain sensibly legible according to thickness, tint, and angle. Avoid the universal “transparent blue glass” look. Add a glass layer only if it serves product truth.

**Optical glass / camera lenses:** create a layered assembly with dark cavities, coated reflections, subtle internal elements, and plausible aperture detail. Do not use a glowing cyan disc as a substitute for a lens.

**Matte polymer:** broader highlights and more diffuse reflections than metal. Avoid making every black plastic component as glossy as piano lacquer.

**Rubber / silicone:** soft specular response and modest highlights. Use seams and microtexture only at a visible scale.

**Ceramic:** use a distinct material response from plastic; a clearcoat may be useful for some finishes but should not make every ceramic object look wet.

**Textile / mesh:** make weave scale plausible. A high-frequency normal map can alias at distance; use mipmaps or filtered textures.

**LEDs and displays:** use emission to represent the light source, but do not overexpose all nearby geometry. A light source and the light it casts are different things; add practical illumination only when appropriate.

## 7.3 Scale and detail

Texture scale is part of product truth. Oversized brushed-metal streaks, giant scratches, oversized pores, thick seams, and giant bevels are common signs that a procedural material has been generated without scale judgment. Use real-world dimensions as a guide. Judge detail at final output resolution.

## 7.4 Color management is mandatory

Incorrect color-space handling causes washed-out textures, crushed tones, oversaturated highlights, or materials that differ between browsers. In Three.js, understand the linear working space and output color space, mark color textures appropriately (usually sRGB), and handle non-color maps as data rather than color. When post-processing is used, the correct output color-space conversion pass may be required. Read the [Three.js color-management manual](https://threejs.org/manual/pages/color-management.html).

Do not randomly mix color values copied from CSS, textures, HDR environments, and shader uniforms without understanding their color spaces. Verify skin tones, neutral grays, whites, and the product's true color on the final output.

---

# 8. Environments and world building

## 8.1 Every environment has a job

The stage should establish product context, scale, mood, material contrast, or the central metaphor. It does not need to be a realistic location; it does need to be coherent.

Useful environment families include:

- **Clean studio:** controlled reflections and maximum attention on shape.
- **Architectural space:** geometric scale, shadow structure, and directional movement.
- **Material world:** a setting built from one surface or substance, such as paper, stone, water, machined metal, or translucent polymer.
- **Abstract graphic stage:** bold fields, lines, or color blocks with a single visual rule.
- **Real environment:** context and human relevance, especially when the product's value appears in use.
- **Surreal but governed world:** impossible geometry or transformation governed by one consistent rule.

Avoid mixing a photorealistic product with an unrelated procedural starfield, holographic grids, floating dust, glowing tunnels, and random landscape elements. If the world is surreal, decide the rule and apply it consistently.

## 8.2 Keep the environment subordinate

Use depth, value contrast, focus, light direction, and movement to separate the hero. Environmental geometry should frame, reveal, or interact with the product. It should not randomly orbit or steal the shot.

A strong background can be extremely simple: a clean plane, gradient-free paper sweep, softly lit wall, or one large geometric form. It becomes deliberate through light, scale, spacing, shadows, and composition.

## 8.3 Atmospheric effects

Fog, haze, volumetrics, and particulate effects are optional and often expensive. Use them when the light actually needs a medium to become visible or when depth hierarchy requires them. Do not cover the scene in fog to hide weak modeling or to make it “cinematic.”

Particles need a physical or graphic reason: debris released by an impact, droplets created by movement through liquid, dust illuminated by a real source, or a purposeful designed swarm. Set a readable size, velocity field, direction, lifespan, and density. Avoid randomly generated confetti with no relationship to the product.

---

# 9. Color direction: intentional, not automatically desaturated

## 9.1 Choose a palette from the concept

Do not start with “dark blue tech.” Start with the product's real colors, target emotion, background contrast, lighting plan, and intended visual identity. Build a limited palette with:

- One dominant field or environment color.
- One product-material family.
- One supporting neutral family.
- At most one or two deliberate accents unless the concept requires a broader color system.

The palette may be monochrome, warm, cool, pastel, saturated, high-contrast, naturalistic, or multicolored. What matters is that it is authored and coherent.

## 9.2 Gradient policy

Gradients are not inherently bad; **default gradients are**. A gradient can create a studio cyclorama, a soft light falloff, a precise color field, a change of depth, or a concept-specific world. It becomes slop when it exists only because modern tech advertising is expected to have glowing blue and purple.

Before using a gradient, state its function. If you cannot, use a solid field or light-created falloff instead. Avoid stacking several different gradients across the background, product, type, glow, and post-processing. Use one gradient as a deliberate compositional tool rather than a decorative substitute for lighting.

## 9.3 Color relationships

- Ensure the product silhouette separates from the background in value as well as hue.
- Use color contrast to guide attention; do not give every object its own accent color.
- When the product already contains strong colors, keep the environment more restrained unless color is the main idea.
- When the product is neutral, the environment may carry a bolder color, but maintain believable surface response and a clean hierarchy.
- Use white, black, and gray deliberately; neutral does not mean lifeless.
- Do not force the image into a cold teal-and-orange grade by default.
- Avoid pushing saturation until material identity is lost.
- Make sure white objects do not disappear against pure white without edge definition, and black objects retain visible form against black.
- Test the grade without bloom and in grayscale to judge value hierarchy.

## 9.4 White and black are not shortcuts

A white studio demands careful shadow control, accurate edge highlights, clean antialiasing, and good exposure. A black studio demands enough reflected structure to show dark materials. Both require craft. Neither is automatically premium.

## 9.5 Palette lock

Lock a palette before final polish. Do not change the background hue from shot to shot unless the shift is narratively motivated. Color continuity helps the audience experience the edit as one film rather than a set of disconnected renders.

---

# 10. Typography and on-screen copy

## 10.1 Type is part of the shot, not a subtitle pasted on later

Treat type as a designed spatial object. Decide how it enters the frame, how it aligns with the product, how long it remains readable, what visual weight it carries, and whether it belongs in the world or on a clean editorial plane.

Use as little copy as the message allows. A single precise line that gives the image meaning is often stronger than a headline, subhead, three feature bullets, a spec cluster, and a call to action all competing in the same shot.

## 10.2 Type hierarchy

At most, a typical product film needs:

1. **Primary statement:** the one line or short phrase that carries the idea.
2. **Supporting detail:** an optional factual qualifier or essential feature.
3. **Brand / product signature:** the name or logo at the resolved ending.

Do not display all three simultaneously unless the composition truly supports it. Let copy appear when the image has earned a moment for reading. Give letters enough time on screen to be read at normal playback speed.

## 10.3 Typeface choice

Do not automatically use SF Pro because the reference is Apple. Apple publishes SF fonts for platform design workflows, and Apple's current font guidance says these typefaces are reserved for system use. Check the current [Apple Fonts page](https://developer.apple.com/fonts/) and the applicable licence before using any font outside its intended context. For a non-Apple brand, choose a legally licensed family that fits its own identity. A neutral sans, contemporary grotesk, humanist sans, or restrained serif can all be valid.

Avoid:

- A random mixture of three or more unrelated type families.
- Ultra-thin type that disappears against reflections or downscaled video.
- Heavy, tightly tracked all-caps as the default luxury treatment.
- Oversized tracking that turns every word into a logo.
- Unnecessary letter-by-letter animation.
- Word spacing, baseline, or optical alignment left to chance.
- Fake small technical labels used only to make a product appear engineered.
- Text rendered inside a 3D mesh when HTML/SVG typography would produce cleaner edges and more reliable spacing, unless true spatial text is the concept.

## 10.4 Typography implementation

Prefer HTML/CSS or SVG for crisp 2D titles over geometry text when it fits the art direction. This enables careful font loading, kerning, tracking, line breaks, optical alignment, and rendering. Use 3D text only when perspective, physical volume, shadow, or interaction with light is essential.

Always verify:

- Fonts finish loading before capture.
- No fallback font appears in exported frames.
- Text is sharp at native resolution.
- Tracking is visually checked, not merely numeric.
- Line breaks are authored, not accidental.
- Contrast holds against moving highlights and changing backgrounds.
- Text safe areas work for intended platforms.
- Legal, technical, or product claims are accurate.

## 10.5 Copywriting tone

Use short, concrete language. Prefer a product truth to a string of unsupported superlatives. “Machined from one piece of aluminum” is stronger when accurate than “The most revolutionary, insanely powerful, unbelievably premium design ever.” Remove filler words, duplicate claims, forced urgency, and generated marketing phrases.

One line should not explain what the image already proves. Let the image demonstrate; let the text focus attention or crystallize meaning.

---

# 11. Motion grammar: timing, weight, and intention

## 11.1 Motion has a verb

Every animated property should have a reason. Use verbs such as reveal, align, open, rotate, travel, separate, assemble, illuminate, compress, expand, focus, pass, settle, or transform. If the motion cannot be described with a clear verb and purpose, reconsider it.

## 11.2 Primary, secondary, and tertiary motion

- **Primary motion** moves the hero object, camera, main typography, or central visual event.
- **Secondary motion** supports the main move: a highlight traveling, a component settling, a cable following, a soft mechanical response, a shadow shifting.
- **Tertiary motion** is optional atmosphere: a few particles, a gentle environmental reaction, subtle screen content.

The hierarchy must remain obvious. Keep the secondary layer restrained. Remove tertiary motion first when a shot feels busy.

## 11.3 Ease is personality

Easing changes the emotional feel of motion. A linear motion can feel mechanical, diagrammatic, or deliberately relentless. A power ease can feel controlled. A custom ease can feel weighty, fluid, or highly authored. Bounce and elastic eases are not premium by default; they work when elasticity is part of the material or concept.

A practical starting vocabulary:

- **Controlled arrival:** a smooth ease-out with a clear deceleration.
- **Intentional departure:** an ease-in that gathers speed without a sudden jump.
- **Elegant travel:** an ease-in-out or a custom curve with long, controlled acceleration and deceleration.
- **Mechanical lock:** a crisp curve with minimal overshoot and a short settle.
- **Soft physical contact:** small, physically motivated settle or compression, only where the object's material permits it.
- **Suspended object:** very slight drift or counter-motion, not a constant bobbing loop.
- **Impact:** fast approach, decisive contact, then a response proportional to mass and material.

Use GSAP's timeline and custom ease tools when they offer better control. See [GSAP Timeline](https://gsap.com/docs/v3/GSAP/Timeline/), [GSAP Easing](https://gsap.com/docs/v3/Eases/), and [CustomEase](https://gsap.com/docs/v3/Eases/CustomEase/).

## 11.4 A product is not a weightless icon

Even a floating hero shot needs a believable relationship to gravity, inertia, or the film's established surreal rule. Avoid constant sinusoidal hovering unless suspension is central to the concept. If an object starts or stops, make the transition feel designed: acceleration, travel, deceleration, and a stable final hold. If it impacts another object, respect relative mass and material.

Do not apply exaggerated bounce to a machined steel enclosure. Do not make a soft silicone object move like rigid metal. Do not rotate a heavy product like a feather unless this contrast is part of the idea.

## 11.5 Avoid “ease soup”

Do not use an unrelated easing style for every layer. A shot should have a coherent motion system. The camera, main object, typography, and support elements can have different curves, but they should feel like they inhabit the same designed event.

Use offsets and overlaps to create richness rather than stacking too many extreme curves. If several objects should arrive together, synchronize them with intentional offsets rather than arbitrary random delays.

## 11.6 Overshoot and settling

Overshoot can help when the object has elasticity or a mechanically plausible settling response. Most precision product reveals benefit from a crisp stop with a tiny, controlled secondary settle at most. Large bounce, elastic scaling, and rubber-band motion make the object feel playful or cheap unless clearly motivated.

If a settle is used:

- Keep the displacement proportionate to the object's apparent mass.
- Resolve the oscillation quickly.
- Avoid a continuing loop after the stop.
- Check for clipping, collision, and intersection at the final pose.
- Ensure the motion improves readability or emotional impact.

## 11.7 Secondary motion follows causes

Examples of valid secondary motion:

- A hinged part begins moving after the main body moves.
- A light reflection changes because the object or light changes relative position.
- A strap, cord, or flexible component follows the rigid body with plausible lag.
- A small mechanical part settles after assembly.
- Fine particles are displaced by a product moving through a medium.
- Screen illumination creates a subtle, motivated reflection on nearby glass.

Invalid secondary motion: everything independently bobbing, every light pulsing, every particle orbiting, and every text element easing in just because the timeline has empty space.

## 11.8 Synchronize action, sound, and edit

The sound should emphasize a meaningful visual beat rather than every transform. Choose the moments that deserve sound: first reveal, contact, alignment, a latch, material change, feature activation, or final lock. Use silence around key sounds so they retain impact.

Do not add generic whooshes to every camera movement. The pitch, envelope, texture, stereo location, and duration should match the perceived material and speed.

## 11.9 Avoid motion as wallpaper

Looping camera drifts, perpetual particle movement, slow rotation, and constant pulsation can make a preview look “alive” while undermining a film's sense of beginning and end. Give shots distinct movement states. If a background loop must run, keep it so subtle that it never competes with the product.

---

# 12. Transition design

## 12.1 A transition should connect meaning

A transition is strongest when the outgoing image already contains the logic of the incoming image. Use a transition because a relationship is visible, not because a plugin provides it.

Approaches:

**Match cut:** align silhouette, shape, direction, color, or camera position across shots.

**Motion continuation:** carry the same direction or velocity into the next shot so the cut feels inevitable.

**Occlusion wipe:** an object passes close to camera and physically blocks the frame, allowing a new setting to appear behind it.

**Light bridge:** a bright reflection or controlled exposure event links shots, but should not become a generic white flash.

**Geometry bridge:** a real contour becomes another contour. The transformation should read clearly and respect spatial continuity.

**Material bridge:** texture or reflection evolves into the next scene's surface language. Use it only when the relationship has meaning.

**Focus bridge:** focus moves to a plane or object that becomes the opening focal point of the next shot.

**Cut on impact or sound:** the cut is motivated by a physical event or musical transient.

**Clean cut:** often the best choice. A precise edit can feel more confident than a visible transition.

## 12.2 Transition rules

- One transition system can unify a film; not every cut needs a special trick.
- Do not use spins, glitch, liquid morphs, light leaks, and particle wipes together without a compelling reason.
- Check object continuity: orientation, scale, travel direction, and apparent velocity.
- Avoid transition elements crossing through solid product geometry unless that interpenetration is intentional and visible as a designed effect.
- Never use a transition to hide a broken product model or an incorrect camera move.
- Test the outgoing and incoming still frames without the transition. Both should be strong.
- Hold long enough after a major reveal for the audience to perceive the result.

## 12.3 Transformations must have rules

When an object transforms, decide what remains conserved: silhouette, volume, material, direction, color, or motion. A transformation that changes everything simultaneously often looks like a random morph. Give the eye an anchor. Use a readable sequence such as alignment → separation → controlled change → reassembly, rather than making the product explode into an untraceable cloud.

## 12.4 Motion and transition technique index

Use this table as a vocabulary when planning a shot. It is a menu of techniques—not a checklist. Choose only the technique(s) that serve the visual thesis.

| Technique | What it communicates well | Best implementation discipline | Common failure |
|---|---|---|---|
| Controlled hero orbit | Form, finish, spatial presence | Short arc with clear start/end compositions | Full endless spin with no new information |
| Macro-to-wide pullback | Connects material detail to whole product | Keep the macro contour recognizable through the move | Abstract close-up goes on too long |
| Light sweep / grazing reflection | Surface quality, edge precision | Move a plausible source or controlled reflection pattern | Flat white stripe pasted on top |
| Exploded assembly | Layering and construction | Separate only meaningful components along readable axes | Parts scatter randomly or collide |
| Mechanical snap / lock | Precision and closure | Tight easing, exact contact, concise sound | Exaggerated bounce and rubbery scaling |
| Slow suspension | Calm, lightness, elegance | Tiny displacement and a stable hold | Constant float loop |
| Kinetic type reveal | Confidence, rhythm, a concise statement | One authored move; keep reading time intact | Every letter animates independently |
| Match cut | Visual continuity across scenes | Match silhouette, axis, scale, or motion direction | Match is only vaguely similar |
| Occlusion transition | Physical continuity between spaces | Let a real object or form cover the camera view | Convenient wipe has no relationship to scene |
| Scale bridge | Connects macro, product, and environment | Maintain a comprehensible relationship of scale | Product appears to teleport or resize arbitrarily |
| Geometric morph | Change, transformation, material concept | Preserve one visual anchor and define the morph rule | Untraceable liquid blob |
| Shadow transition | Light, depth, reveal | Use a motivated source and coherent shadow direction | Shadow becomes a black overlay |
| Reflection bridge | Connects two visual worlds | Let a reflected form become the next image's shape | Mirror image changes with no spatial logic |
| Focus pull | Directs attention to a detail or new plane | Plan the focus distance and hold on the new target | Blur obscures key information |
| Speed ramp | Emphasizes acceleration or impact | Give the ramp a clear beat and a smooth curve | Random slow-fast-slow motion every shot |
| Motion-blur streak | Direction and velocity | Match shutter feeling and object trajectory | Smear artifacts or blurred static graphics |
| Particle dissolve | Disassembly, release, energy | Seed randomness; preserve silhouette during early breakup | Generic glowing dust explosion |
| Line draw / contour trace | Shape, precision, geometry | Trace a meaningful edge and stop at a defined endpoint | UI-style outline around everything |
| Repetition / array | Rhythm, modularity, scale | Align repeats with a consistent rule | Infinite tunnel with no hierarchy |
| Rotational handoff | Energy, transition between objects | Match angular direction and perceived momentum | Spin-for-spin's-sake |
| Wipe by product surface | Tactile continuity | Use close foreground geometry to create a real occlusion | Wipe clips through solid geometry |
| Color-field change | Change of chapter or product variant | Change color on an intentional editorial beat | Background changes hue arbitrarily |
| Emissive activation | Function, power, attention | Use limited luminance and a believable response | Entire product glows as generic sci-fi |
| Environmental ripple | A feature's effect on its surroundings | Tie amplitude, direction, and timing to a real cause | Stock concentric circles for any feature |
| Final settle / hero lock | Confidence and closure | Finish with a still, readable, balanced image | End frame remains drifting or overdecorated |

Before using a technique, answer: **what information or emotion does this technique create that a clean cut or simpler move would not?** If the answer is vague, prefer the simpler option.

---

# 13. Three-dimensional spectacle with restraint

Use 3D effects to communicate product truth, scale, function, or the governing visual idea. This section covers common techniques and their discipline.

## 13.1 Exploded views and assembly

Exploded views are appropriate for modular products, mechanisms, layered construction, or a technical feature. Each component should follow a coherent axis and stop in a designed pose. Keep component count low enough that viewers can parse the construction.

- Preserve the true relationships between pieces.
- Maintain a consistent coordinate system.
- Avoid random radial dispersal unless radial assembly is part of the design.
- Keep parts from intersecting or clipping through one another.
- Make the final assembly motion precise and satisfying.
- Keep the model aligned with its final hero pose, so the reassembly feels like the same physical product.

## 13.2 Macro travel

A macro dive is not a random flight through holes and crevices. Establish the larger shape before entering an intimate detail, orient the viewer with clear edges, then use the macro view to reveal one meaningful construction or material feature. Return to a clear product context unless the film intentionally ends abstractly.

## 13.3 Shape repetition

Repeating a product or one of its signature shapes can create rhythm, scale, or a graphic pattern. Maintain a compositional rule: spacing, alignment, perspective, synchronized movement, or a deliberate break in the pattern. Avoid “infinite product tunnel” scenes whose only purpose is to fill the frame.

## 13.4 Particles and simulations

Particles should have an authored motion field and a reason to exist. Use a simple simulation before a complex one. Define:

- Spawn region.
- Initial speed and direction.
- Forces or target paths.
- Lifetime and fade profile.
- Size distribution and depth relationship.
- Density at the hero's silhouette.
- What triggers the particle event and when it stops.

Avoid uncontrolled random seeds if the output must be reproducible. Seed the random generator so rerenders match. A few well-placed elements often communicate more clearly than a million points.

## 13.5 Fluids, smoke, cloth, and deformation

Realistic fluid and deformation simulation can exceed what a real-time WebGL scene can do simply. Do not pretend a handful of oscillating points is liquid. Use stylized motion only if it is a conscious art direction. Prefer a clear silhouette and controlled surface behavior over an expensive effect with unstable artifacts.

For a liquid-like event, decide whether it is a physically credible fluid, a graphic ribbon, a surface morph, or a fluid-inspired abstraction. Each is valid if executed consistently. Do not mix their visual cues accidentally.

## 13.6 Light beams and laser lines

A beam is visible only through an appropriate medium or graphic treatment. A line that appears to illuminate geometry should have consistent direction and falloff. Avoid using bright outlines to trace every edge. A single line can be an important visual motif; dozens often create the “tech HUD” cliché.

## 13.7 Bloom, glare, and lens effects

Bloom is a high-risk effect because its failure is obvious. Use it only around actual bright sources and high-value highlights. Begin with bloom disabled, then add the weakest amount that supports the idea. Protect the product's edge definition. Strong bloom can erase detail and flatten materials.

Post-processing is a deliberate finishing stage—not a way to repair bad assets or lighting. With Three.js, `EffectComposer` manages post-processing passes and `UnrealBloomPass` is an optional bloom pass. Use those capabilities sparingly and read the [EffectComposer](https://threejs.org/docs/pages/EffectComposer.html) and [UnrealBloomPass](https://threejs.org/docs/pages/UnrealBloomPass.html) documentation before adding them.

---

# 14. Sound design and rhythm

A product film is audiovisual even if the implementation is code-driven. A silent visual can still be strong, but the final cut should not treat sound as a last-minute layer.

## 14.1 Create a sound map with the storyboard

For every shot, list the intentional sonic events:

- Ambient bed or silence.
- Low-frequency weight or body tone.
- Mechanical clicks, locks, slides, or contact sounds.
- Texture sounds such as brushed metal, fabric, glass, ceramic, or rubber.
- Musical pulse and transitions.
- The one or two hero accents.
- Final resolution or silence.

Every sound must correspond to a physical event, an emotional cue, or a deliberate abstract motif. If a sound cannot be explained, remove it.

## 14.2 Sound envelopes

Think about attack, body, decay, and space. A sharp latch might need a concise transient with a short, clean decay. A large reveal may benefit from a low-frequency swell followed by room-like space. A small polished metal part should not sound like a giant steel door. Avoid stacking multiple bass impacts because a scene has a big visual change.

## 14.3 Sound and timing

Place the sound event against the actual frame where the event occurs. If the product locks into place on frame 720, the sound's attack should align to that frame, not merely start in the same half-second. Build small visual anticipations or settles around sonic transients only when they reinforce the action.

## 14.4 Rights and originality

Use appropriately licensed music and sound effects or original procedural/generated sound design. Do not copy Apple's music choices, signature sound design, or exact campaign audio. Do not include copyrighted tracks merely because they fit the reference film.

---

# 15. Code architecture for reliable motion

Use the smallest stack that can achieve the quality target. More libraries do not automatically yield better art direction.

## 15.1 Recommended roles

**HTML/CSS:** clean delivery shell, title typography, SVG graphics, compositional overlays, safe areas, and debug controls during development.

**Three.js:** real-time WebGL scene, camera, geometry, materials, lighting, textures, shadows, and post-processing.

**GSAP:** timeline orchestration, authored easing, sequencing, overlap, and deterministic control of animated properties.

**Canvas / custom shaders:** procedural textures, particles, custom transitions, optical effects, and special rendering where standard materials do not suffice.

**glTF/GLB + GLTFLoader:** when externally authored 3D assets are supplied and licensed. Confirm model scale, normals, UVs, animation clips, materials, and texture color spaces.

**Remotion (optional):** if the project allows React and a render pipeline, it can create frame-addressable compositions and render videos through its documented workflow. It is not required for a standalone HTML deliverable. See [Remotion rendering](https://www.remotion.dev/docs/render) and [timing](https://www.remotion.dev/docs/timing).

**FFmpeg (optional post stage):** recommended for reliable encoding, frame assembly, audio muxing, frame-rate conversion where needed, and final metadata. It is a render tool, not a generative video model. If the brief explicitly demands only a single HTML output and no external processing, prioritize a browser-based preview/export and document browser codec constraints in comments.

Do not introduce React, a bundler, a physics engine, a shader framework, or a second animation library unless it solves a defined problem. A tiny custom easing helper can be better than adding another dependency.

## 15.2 Define a single master timeline

All shot timing should live in one master timeline or in clearly controlled child timelines. Do not scatter dozens of unrelated `setTimeout` calls across the code. A master timeline gives the film a single source of truth for timing, overlaps, cuts, and final duration.

Conceptual architecture:

```js
const FPS = 60;
const DURATION_SECONDS = 20;
const DURATION_FRAMES = FPS * DURATION_SECONDS;

// Create and configure scene, camera, product, environment, and lights first.
// Then author one timeline that animates their properties.
const master = gsap.timeline({ paused: true });

// master.to(product.position, { x: 0.2, duration: 1.2, ease: customEase }, 0.8);
// master.to(camera.position, { z: 4.0, duration: 2.0, ease: "power2.inOut" }, 2.0);
// Add all remaining shot events with explicit timeline positions.

function renderAtSeconds(seconds) {
  const t = Math.max(0, Math.min(DURATION_SECONDS, seconds));
  master.time(t, false); // Apply the exact authored timeline time.
  // Update custom simulations from t here, never from an unbounded random tick.
  renderer.render(scene, camera);
  // If the composition includes DOM text, capture the complete page via a browser
  // screenshot pipeline or render text in SVG/Canvas so it shares the frame capture.
}
```

This is a structural example, not a finished scene. In production, verify the actual time units and callback behavior of the library version you load. Add an explicit initialization state so rendering cannot begin before the scene, textures, and fonts are ready.

## 15.3 Deterministic animation

A film is a timeline; it should not depend on how fast a viewer's computer happens to render. For final frame rendering, calculate scene state from an explicit timestamp or frame number.

Preferred deterministic model:

```js
function renderFrame(frameIndex) {
  const frame = Math.min(DURATION_FRAMES - 1, Math.max(0, frameIndex));
  const seconds = frame / FPS;
  setAuthoredTimelineTime(seconds);
  updateProceduralElementsAt(seconds);
  renderer.render(scene, camera);
  return seconds;
}
```

Avoid relying on accumulated real-time deltas for anything that must be reproducible. Instead of `position.x += velocity * delta`, calculate the value from normalized time, a precomputed curve, a seeded simulation, or a deterministic state step. If a physics simulation is necessary, advance it using a fixed time step and a reproducible initial state.

For GSAP, use a paused master timeline and set its explicit time/progress for frame capture. Ensure callbacks that spawn particles, change random values, or mutate state do not behave differently when the timeline is jumped directly to an arbitrary time. Prefer pure functions for procedural motion: `positionAt(t)`, `rotationAt(t)`, `opacityAt(t)`.

## 15.4 Preview time versus render time

Use real-time `requestAnimationFrame` for an interactive preview. Use a separate frame-addressable mode for final renders. Do not assume a preview that looks smooth at 60 FPS will automatically produce a deterministic 60 FPS export. Real browsers throttle background tabs and frames can be dropped under load.

GSAP's ticker is tied to browser rendering updates, and its docs explain how frame updates can be throttled and how lag smoothing affects perceived timing. That is useful for interactive playback but is another reason to use an explicit frame-time path for offline or repeatable rendering. See [GSAP ticker](https://gsap.com/docs/v3/GSAP/gsap.ticker/) and [MDN animation performance](https://developer.mozilla.org/en-US/docs/Web/Performance/Guides/Animation_performance_and_frame_rate).

## 15.5 Frame rate and motion cadence

Choose the output frame rate intentionally:

- **24 fps:** familiar cinematic cadence; requires disciplined shutter/motion-blur handling for fast action.
- **25 fps:** useful for certain broadcast workflows.
- **30 fps:** common digital/social delivery and a useful real-time compromise.
- **60 fps:** crisp motion, useful for fast product movement and high-detail technical reveals; it can feel clinical if all motion is too perfect and the sound/edit has no expressive rhythm.

Do not choose 60 fps merely because it sounds high quality. Pick the rate that suits movement and delivery. Author your keyframes independently from preview refresh rate.

## 15.6 Motion blur

Native real-time WebGL rendering does not automatically provide the same temporal integration as an offline film renderer. A moving object that looks too digitally crisp can sometimes benefit from motion blur, but a fake blur smear can create the opposite problem.

Options, from most dependable to least:

1. Render multiple temporal samples around each target timestamp and combine them when your pipeline supports this reliably.
2. Use a vetted motion-blur post-process if the plugin and effect are stable at final resolution.
3. Render at a higher temporal or spatial sampling rate and perform carefully controlled downsampling, if the render pipeline supports it.
4. Keep motion slower and more deliberate when the output cannot support good blur.

Do not pretend that CSS `filter: blur()` is a complete camera shutter model. Do not add blur to static type or the product's essential silhouette. Inspect motion blur frame by frame for ghosting and transparency artifacts.

## 15.7 Output resolution and aspect ratio

Use the required delivery dimensions, not merely the current browser window. Common formats include:

- 3840×2160 UHD (16:9).
- 1920×1080 Full HD (16:9).
- 1080×1920 vertical (9:16).
- 1080×1080 square (1:1).
- Other dimensions only when the brief requires them.

Set renderer size and pixel ratio deliberately. Be aware that high device pixel ratios multiply render workload. For preview, cap pixel ratio to avoid wasting GPU time; for final capture, render at exact target dimensions. Avoid using a low-resolution composition scaled up at export. Recompose for vertical or square versions rather than blindly cropping a 16:9 master.

## 15.8 Browser export and capture

`HTMLCanvasElement.captureStream()` and `MediaRecorder` can capture rendered output in supported browsers, and a canvas track may support explicit `requestFrame()` capture in some implementations. Browser codec support, timestamp handling, audio capture, and frame pacing vary; `requestFrame()` is not universally available. See [MDN captureStream](https://developer.mozilla.org/en-US/docs/Web/API/HTMLCanvasElement/captureStream), [requestFrame](https://developer.mozilla.org/en-US/docs/Web/API/CanvasCaptureMediaStreamTrack/requestFrame), and [MediaRecorder](https://developer.mozilla.org/en-US/docs/Web/API/MediaRecorder).

For a reliable high-quality master, prefer rendering a sequence of numbered frames at exact timestamps, then assembling them with a video encoder and syncing audio to the same timeline. If titles are rendered in the DOM while the 3D scene is on a canvas, capture the full browser composition or render the titles through SVG/Canvas so they are included. Never deliver a silent or incomplete video while claiming it contains audio.

## 15.9 One-file constraints

When one self-contained HTML file is explicitly required:

- Inline authored CSS and JavaScript when practical.
- Use pinned dependency versions; avoid unpinned “latest” CDN paths.
- Make all external URLs explicit and handle loading failures gracefully.
- Do not assume a local file URL will allow every module import or cross-origin asset load. Prefer a local HTTP server for development, and document the needed serving mode in a comment if the output contract allows it.
- Avoid network-dependent assets when the brief requires offline operation. Embed small original SVGs or procedural assets if reasonable; large textures and models may make the file impractical.
- Keep one authoritative initialization path.
- Expose a clear `renderAtSeconds()` or equivalent function for frame-addressable rendering.
- Add development diagnostics behind a `DEBUG` flag, and remove them from the final composition.
- Do not use a UI framework just to display a full-screen canvas and a few text overlays.

---

# 16. Performance, stability, and browser behavior

## 16.1 Design for a target machine

A beautiful scene that drops frames, overheats, or crashes is not production-ready. Start with the target browser, GPU, screen resolution, and output frame rate. Use adaptive settings for previews, but keep final render settings explicit.

## 16.2 Performance priorities

1. Reduce unnecessary draw calls and redundant materials.
2. Reuse geometries and materials where possible.
3. Keep texture dimensions proportionate to their visible contribution.
4. Use instancing for repeated geometry when appropriate.
5. Avoid transparent overlapping layers that create fill-rate and sorting costs.
6. Use expensive shadows only when they noticeably improve the image.
7. Reduce particle counts before removing essential product detail.
8. Use sensible anti-aliasing and render scale; test at final size.
9. Dispose of unused geometry, materials, render targets, textures, and post-processing passes.
10. Monitor GPU time and memory; frame rate alone may hide intermittent stalls.

## 16.3 Avoid common browser animation failures

- No animation loop should start before core objects exist.
- Do not reference `const` or `let` variables before initialization; temporal-dead-zone errors can stop the entire film.
- Register callbacks after state has been initialized, or guard callback execution until ready.
- Make resize logic update camera aspect/projection and renderer size correctly.
- Prevent duplicated event listeners and duplicate animation loops after reinitialization.
- Do not resize high-resolution render targets on every frame.
- Handle `prefers-reduced-motion` for interactive previews when applicable; see [MDN's media-query reference](https://developer.mozilla.org/en-US/docs/Web/CSS/@media/prefers-reduced-motion).
- Avoid unpredictable `Math.random()` values in the render path.
- Handle failed textures, unavailable fonts, and failed WebGL initialization with clear errors or fallbacks.
- Make the end frame reproducible and ensure the animation does not continue into another loop after the intended duration.

## 16.4 Accessibility and comfort

Even in a cinematic preview, do not rely on rapid flashing, repeated high-contrast strobing, or aggressive camera motion that makes people uncomfortable. Apple’s UI-specific guidance recommends purposeful motion and attention to motion comfort; its accessibility guidance also warns about fast-moving and blinking effects. Apply those concerns sensibly to films. A short advertisement can be expressive without using sustained flashing or disorienting screen-space movement.

## 16.5 Error handling and debugging

The agent must inspect the actual browser console and rendered output, not infer correctness from code alone. Catch and fix:

- Uncaught exceptions.
- Failed imports / 404s.
- Shader compilation errors.
- Texture or font loading failures.
- `NaN` values in transforms, colors, or camera parameters.
- Objects outside the frustum unexpectedly.
- Incorrect alpha sorting, clipping, z-fighting, and shadow acne.
- Flickering geometry or aliasing.
- Timeline events that end beyond the duration.
- Animation callbacks that run twice or spawn duplicate objects.
- Exported video with wrong dimensions, frame rate, duration, or audio sync.

Do not patch a broken variable reference with a random value if the underlying initialization order is wrong. Fix the dependency and lifetime of the state.

---

# 17. Production workflow: from brief to final render

Follow the sequence. Do not skip directly from prompt to a large, complex implementation.

## Phase 1 — Brief decomposition

Extract:

- Brand and product.
- Audience and intended feeling.
- One primary message.
- Exact product truth / specification that may be shown.
- Duration and aspect ratio.
- Frame rate and required output format.
- Brand assets, typefaces, sound and licensing constraints.
- Whether the file must be a single HTML document.
- Whether external assets or CDNs are allowed.
- Whether export must happen inside the browser or can use FFmpeg/Remotion.

If critical information is missing, make a conservative, explicit assumption and proceed when possible. Ask only for details that materially change the film, such as missing product references or an exact brand logo. Do not hold the entire task hostage to nonessential preferences.

## Phase 2 — Reference gathering

Select three to five relevant references, not fifty unrelated ones. Include one reference for the camera language, one for materials/lighting, one for edit rhythm or storytelling, and one for typography/brand finish. At least one should be outside the Apple catalogue so the output develops an original point of view.

Create a short evidence sheet containing:

- Source URL and title.
- What the reference demonstrates.
- What not to copy.
- Which principle will be reinterpreted.

The final creative direction should not depend on copyrighted source assets unless properly licensed.

## Phase 3 — Concept and shot design

Write the visual thesis and anti-thesis. Create a storyboard with a small number of purposeful shots. For each shot, establish start frame, end frame, movement, light, color, transition, text, and sound. At this stage, a clean sequence of still frames is enough to validate the idea.

Do not make the first storyboard a library of effects. Design the visual story.

## Phase 4 — Gray-box prototype

Build only:

- Accurate primary product geometry or the available product asset.
- Camera and basic stage.
- Placeholder light shapes and materials.
- Main transforms and cut timings.

Use plain gray material if necessary. Ignore tiny details, particles, bloom, and sound until camera and composition work.

Review the entire timeline with the product represented as simply as possible. If the animation is not interesting without visual polish, fix the action before adding polish.

## Phase 5 — Materials and lighting

Lock the geometry and camera enough that you can evaluate material response. Add material differences, environment reflections, the light setup, shadows, and only necessary texture detail. Inspect hero frames as still images. Correct model or camera errors before touching post-processing.

## Phase 6 — Motion refinement

Shape velocity curves, contact, starts, stops, settling, offsets, and transition logic. Remove unnecessary motion. Check each shot both in real time and at individual frames. Pay particular attention to the first and last frames of every shot: many defects are hidden during motion but obvious in stills.

## Phase 7 — Typography and sound

Add copy only after the visual hierarchy is stable. Check reading duration and contrast. Add a sound map, use licensed/original sounds, and sync each event to a precise timeline moment. Leave some moments quiet.

## Phase 8 — Final look

Apply output color management, subtle post-processing if needed, and a controlled grade. Review the image without effects, then with effects. If the effects do not improve the image, remove them. Ensure color continuity and highlight detail across cuts.

## Phase 9 — Test and render

1. Run the full sequence from 0 to the final frame.
2. Inspect the console for errors and warnings.
3. Render contact sheets for the opening, every major transition, each hero hold, and the ending.
4. Inspect the exact start, midpoint, and end of every shot.
5. Watch the whole film at normal speed and at reduced speed to diagnose easing only. Do not judge final rhythm solely from slowed playback.
6. Review a low-bitrate or reduced-resolution preview to catch compositional legibility problems.
7. Render at target resolution and frame rate.
8. Verify duration, dimensions, frame rate, audio, and opening/closing frame.
9. Confirm no debug overlays, cursor, browser UI, default error screens, or missing assets appear in the final output.

## Phase 10 — Final quality pass

Do not stop at “it runs.” Stop when:

- The core idea is immediately understandable or intriguingly legible.
- The product looks intentional and believable from every shown angle.
- Camera motion feels authored, not random.
- The lighting explains surfaces and shape.
- The color system is coherent.
- Every effect has a reason.
- Text is aligned, readable, and correctly spelled.
- Transitions feel connected.
- The sound is balanced and synchronized, if included.
- The ending has a stable, memorable resolution.
- The render is reproducible and technically clean.

---

# 18. Recommended 20-second structure

This is a starting pattern for a short product film—not a mandatory Apple formula. Adapt beat lengths to the product, the visual concept, and delivery platform.

| Time | Dramatic function | Typical visual strategy | Must be true by the end |
|---|---|---|---|
| 0.0–2.0 s | Hook | A striking silhouette, precise macro, unexpected scale, or meaningful physical event | Viewer has a reason to continue watching |
| 2.0–5.0 s | Orientation | Establish full product or connect the macro to the whole object | Viewer understands what the subject is |
| 5.0–9.0 s | Revelation | Camera move, construction reveal, material transformation, or feature demonstration | The core benefit becomes visible |
| 9.0–13.0 s | Proof / escalation | Demonstrate the feature, performance, or emotional payoff in one elegant sequence | The idea feels credible and specific |
| 13.0–16.5 s | Hero moment | Strongest composition, controlled motion, clear read | Viewer can absorb and remember the product |
| 16.5–20.0 s | Resolution | Product locks into final pose; concise line and/or brand mark appears | A confident final image remains in memory |

Do not force every film into these exact timings. A 20-second film can use three shots or ten; the number of shots is secondary to communication. Do not use the ending as a crowded recap slide.

## Timing guidance

- The hook should not spend two seconds showing an empty space with a tiny glowing point unless that is a deliberate, compelling reveal.
- Establish enough context for the viewer to understand the macro or abstract opening.
- Give the main feature the largest share of visual attention.
- Avoid placing the product name, logo, tagline, and every specification on the final frame.
- Preserve a readable final hold; do not end mid-motion unless a loop is explicitly required.
- A silent pause immediately before a reveal can be more powerful than another sound effect.

---

# 19. Shot-design patterns that often work well

The following are **building blocks**, not presets. Choose at most a few that fit the concept.

## Pattern A — Edge to object

1. Begin on a distinctive edge or contour, not an anonymous shiny surface.
2. A reflection moves across it and identifies the material.
3. The camera tracks or pulls away until the contour becomes recognizable as part of the product.
4. Hold on the full hero view.
5. Use the contour or camera direction to connect to the next shot.

Risk: the opening macro looks like generic metal or glass for too long. Solve it by revealing recognizable geometry early enough.

## Pattern B — Controlled assembly

1. Show the core body or silhouette.
2. Separate only the components that explain the construction.
3. Move the pieces along authored, legible axes.
4. Pause when the relationship between parts is clear.
5. Reassemble with precise timing and a restrained mechanical sound.
6. Finish in a pristine hero pose.

Risk: too many parts turn the shot into a confusing technical animation. Limit the part count and preserve the product's coordinate system.

## Pattern C — Feature as physical event

1. Establish the product in a clean, stable view.
2. Identify the active feature through a distinct visual cue.
3. Make the feature perform its real function.
4. Show the effect it has on the world, display, user, or surrounding object.
5. Return to the product or reveal the benefit in a human context.

Risk: the effect becomes a generic wave, particle burst, or energy ring. Prefer a specific and credible manifestation of the feature.

## Pattern D — Material transformation

1. Start with a surface at macro scale.
2. Reveal its grain, reflection, translucency, or structure.
3. Let an elegant camera or geometric relationship connect the material to the product's complete form.
4. Do not morph unrelated shapes without a perceptible bridge.
5. End on a stable material read.

Risk: procedural textures or transformations look like fluid AI imagery. Use controlled geometry, physically plausible material response, and consistent visual rules.

## Pattern E — Graphic precision

1. Choose a single shape, line, or rhythm extracted from the product.
2. Build a clean graphic field around it.
3. Animate the graphic with a consistent rule: spacing, alignment, repetition, scale, or direction.
4. Let the product appear as the source of the rule, not an unrelated object pasted into the frame.
5. Reduce the graphic system as the product becomes fully visible.

Risk: it turns into generic motion-graphics grids. Keep only elements that explain the object or structure the composition.

## Pattern F — Human outcome

1. Show a human action, need, or moment with emotional precision.
2. Introduce the product naturally, rather than making the person pose with it.
3. Let the product change the experience through a credible action.
4. Use sound, light, or editing to express the feeling of that change.
5. Leave the viewer with the human outcome, not simply a specification list.

Risk: a product spot becomes a generic lifestyle montage. Choose one human moment that communicates the idea with specificity.

---

# 20. Creative review rubric

Score each dimension from 1 to 5. A project is not ready if any critical dimension scores 1 or 2, even if its technical spectacle is strong.

| Dimension | 1 — Weak | 3 — Solid | 5 — Exceptional |
|---|---|---|---|
| Concept | Generic visual collection | Clear idea with a few original choices | One compelling, memorable idea governs the entire film |
| Product fidelity | Incorrect proportions/details | Mostly credible with minor compromises | Silhouette, construction, material response, and key details feel authored and true |
| Composition | Busy or arbitrary | Clear subject and workable framing | Every frame has deliberate balance, scale, negative space, and hierarchy |
| Camera | Random orbit/drift | Smooth and understandable | Moves reveal information with precise starts, paths, and endings |
| Motion | Uniform easing / floaty movement | Consistent, mostly purposeful | Motion has a distinct physical and emotional character |
| Lighting | Flat, blown-out, or glow-dependent | Good basic separation and form | Reflections and contrast explain every important material and contour |
| Materials | All surfaces look alike | Recognizable material differences | Each surface has controlled, physically persuasive light response |
| Color | Trend-driven or incoherent | Restrained, functional palette | Product-specific, consistent, memorable color direction |
| Typography | Random, cramped, or unreadable | Clear hierarchy and spelling | Deliberate placement, rhythm, tracking, contrast, and reading time |
| Transitions | Effects used as decoration | Clear cuts and a few motivated transitions | Every transition reinforces spatial or conceptual continuity |
| Sound | Generic whooshes or poor sync | Adequate mix and alignment | Precise, material-specific sound that contributes to the story |
| Technical quality | Errors, flicker, stalls, missing assets | Plays correctly | Reproducible render, clean console, stable frames, no visible defects |
| Ending | Abrupt or cluttered | Product and brand resolve | Final frame is composed, memorable, and held with confidence |
| Originality | Template-like imitation | Some distinct visual decisions | Clearly informed by references yet unmistakably its own work |

**Approval threshold:** aim for 4 or better on concept, fidelity, composition, camera, motion, lighting, and technical quality. An exceptional score in particles, post-processing, or shader complexity cannot compensate for a weak idea.

## Final anti-slop audit

Answer yes to each:

- Can I explain why every effect exists?
- Does the color palette belong to this product and concept?
- Are the camera and product movements intentional and coordinated?
- Is the most interesting visual feature also the most legible one?
- Is the product physically and visually credible?
- Is text present only where it helps?
- Does the film contain a quiet moment or clear hierarchy rather than constant activity?
- Are transitions motivated by image, action, or sound?
- Does every major still frame work without animation?
- Does the ending resolve rather than simply stop?
- Would the design still be recognizable without generic tech motifs?
- Have I inspected the complete render and browser console?

If any answer is “no,” revise before adding new detail.

---

# 21. Implementation acceptance checklist

Before delivering code:

## Creative
- [ ] Visual thesis and primary message are clear.
- [ ] The first shot earns attention without relying on default glow or particle presets.
- [ ] There is a clear progression rather than an unrelated sequence of effects.
- [ ] Product truth is preserved.
- [ ] The visual system is original and suitable for the brand.
- [ ] Color, light, motion, and type all support the same idea.

## Animation
- [ ] One master timeline governs key events.
- [ ] All camera moves have authored start/end poses and easing.
- [ ] Secondary movement is limited and motivated.
- [ ] No component passes through the product unintentionally.
- [ ] No unintended loops, jumps, transform snaps, or overlapping timeline events.
- [ ] End frame is stable and intentionally composed.

## Engineering
- [ ] Correct libraries and pinned versions are loaded.
- [ ] Scene initialization order is valid.
- [ ] No temporal-dead-zone errors or callbacks referencing undefined variables.
- [ ] No uncaught console errors.
- [ ] Render dimensions and aspect ratio are exact.
- [ ] Fonts and textures are loaded before capture.
- [ ] Random procedural behavior is seeded or deterministic.
- [ ] Full-duration rendering works from start to finish.
- [ ] Export frame rate, duration, encoding, and audio were verified where applicable.
- [ ] All assets are licensed, supplied by the user, or original.

## Anti-slop
- [ ] No default blue/purple/green glow motif remains without a written concept reason.
- [ ] No decorative HUD, rings, numbers, particles, or grids remain without purpose.
- [ ] No excessive bloom, chromatic aberration, fake film grain, or lens flare.
- [ ] No generic floating-card interface has crept into a film-only request.
- [ ] The product remains the visual hero.
- [ ] Effects have been removed wherever they fail to improve communication.

---

# 22. How an agent should respond to a coding brief

When this skill is active, follow this execution contract.

1. **Read the brief completely.** Extract deliverable format, duration, output resolution, brand, product truth, and tooling limits.
2. **Research only what matters.** Use a small, relevant set of references, inspect the actual videos, and capture the principles rather than copying the footage.
3. **Make a concept decision.** Do not return ten safe concepts unless the user explicitly asks for options. Commit to one strong, defensible direction.
4. **Storyboard and time the film.** Write the shot table and map important beats to exact timeline seconds or frames.
5. **Prototype the critical moment first.** Build the shot that determines whether the idea works. Validate silhouette, camera, and light before constructing the full sequence.
6. **Implement the full timeline.** Keep the architecture deterministic, readable, and easy to tune.
7. **Run it.** Do not claim that code is tested unless it was actually executed. Inspect the console and capture representative frames when tools permit.
8. **Iterate on visible defects.** Fix composition and geometry before visual effects. Fix motion curves before adding more transitions. Fix lighting before adding bloom.
9. **Verify deliverables.** Ensure the requested file(s) exist and the output contract is followed exactly.
10. **Be honest about limits.** If true photorealism depends on unavailable CAD, HDR environments, textures, audio assets, or a rendering environment, state or encode that limitation clearly instead of disguising a guess as a verified result.

## Default decision order when quality and complexity compete

Prefer, in this order:

1. Better concept.
2. Better product silhouette and geometry.
3. Better composition.
4. Better light and materials.
5. Better motion and timing.
6. Better edit and sound.
7. Carefully chosen post-processing.
8. More geometry, particles, and effects only if the concept needs them.

Do not reverse the order.

## What to do when the brief says “make it more cinematic”

Do not automatically darken everything, increase bloom, add lens flares, add grain, or slow every move down. Diagnose the actual weakness:

- If the image is flat, improve light direction, reflections, composition, and contrast.
- If the product feels cheap, improve silhouette, edge radii, normals, and material response.
- If motion feels generic, redesign the velocity curve and relationship between camera and subject.
- If the sequence has no emotional arc, change the shot order and reveal.
- If the result looks synthetic, fix proportion, texture scale, lighting, contact shadows, and motion physics before adding imperfections.
- If the film feels noisy, remove effects and strengthen hierarchy.
- If it feels empty, introduce a meaningful event, a deliberate graphic rule, or human context—not random decoration.
- If it feels slow, tighten setup or clarify the action; do not simply increase every animation speed.
- If it feels rushed, add a hold at the moment of greatest comprehension or emotional payoff.

## What to do when the brief says “make it Apple-like”

Interpret this as a request for product-first art direction, precise motion, controlled materials, high visual finish, and confidence in editing. Do **not** automatically make the scene white, use Apple's typography, reproduce an Apple keynote layout, add a minimal centered title, or imitate a recognizable campaign. Choose an original visual system that reaches a similar level of intention and craftsmanship.

---

# 23. Source register and further study

The sources below are the research foundation for this skill. Apple HIG sources cover interactive software design, so their principles are adapted—not misrepresented as official ad-film rules. The film case studies include official archives and reputable production/critical sources. Technical references should be revisited when library versions change.

## Apple design principles and motion

1. [Apple Human Interface Guidelines — Design Principles](https://developer.apple.com/design/human-interface-guidelines/design-principles) — Purpose, agency, flexibility, simplicity, craft, and delight; useful as a values framework.
2. [Apple Human Interface Guidelines — Motion](https://developer.apple.com/design/human-interface-guidelines/motion) — Purposeful motion, restraint, feedback, and comfort. Written for apps and games, not advertising films.
3. [Apple Human Interface Guidelines — Color](https://developer.apple.com/design/human-interface-guidelines/color) — Judicious use of color, contrast, accessibility, and color management.
4. [Apple Human Interface Guidelines — Branding](https://developer.apple.com/design/human-interface-guidelines/branding) — Brand identity, judicious use of brand color, typography choices, and allowing content to lead.
5. [Apple Human Interface Guidelines](https://developer.apple.com/design/human-interface-guidelines) — The broader foundation covering color, typography, layout, materials, accessibility, and related topics.
6. [Apple Fonts](https://developer.apple.com/fonts/) — Official information on SF Pro, SF Compact, SF Mono, and other font families; check current terms before use.
7. [Apple Developer — Fonts technology overview](https://developer.apple.com/documentation/technologyoverviews/fonts) — Typeface families and guidance about system font APIs and reserved fonts.
8. [Apple App Store Marketing Resources and Identity Guidelines](https://developer.apple.com/app-store/marketing/guidelines/) — Specific guidance for marketing apps on Apple platforms, including product image and video rules. Apply only within its stated context.

## Apple campaigns, films, and production analysis

9. [Apple Events archive](https://www.apple.com/apple-events/) — A starting point for comparing product keynote films and reveal sequences across time and categories.
10. [Apple Newsroom — “One Night on iPhone 7”](https://www.apple.com/newsroom/2017/01/behind-apples-new-campaign-one-night-on-iphone-7/) — An official account of the *Shot on iPhone* low-light photography campaign.
11. [Apple — “Behind the scenes: An Apple Event shot on iPhone”](https://www.youtube.com/watch?v=V3dbG9pAi8I) — Official production commentary on an event shot with iPhone 15 Pro, including professional capture and post workflows.
12. [Adweek — “This Look Inside Spike Jonze's Apple Ad Is as Fascinating as the Film Itself”](https://www.adweek.com/creativity/this-look-inside-spike-jonzes-apple-ad-is-as-fascinating-as-the-film-itself/) — Production breakdown for *Welcome Home*, especially choreography and practical set design.
13. [Fast Company — “The Ingenious Design Behind Apple's Viral HomePod Ad”](https://www.fastcompany.com/90165359/the-ingenious-design-behind-apples-viral-homepod-ad) — Practical mechanics, choreography, and timing in the HomePod film.
14. [MacRumors — Behind-the-scenes video on the HomePod ad](https://www.macrumors.com/2018/03/22/behind-scenes-spike-jonze-homepod/) — Summary of the production documentary and the use of practical effects.
15. [Oxford Academic — “Dancing Silhouettes: The Mobile Freedom of iPod Commercials”](https://academic.oup.com/edited-volume/28293/chapter-abstract/214507151) — Scholarly context for the iPod silhouette campaign's imagery and messaging.
16. [Taylor & Francis — “The Force of Callas’ Kiss: The 1997 Apple Advertising Campaign, ‘Think Different’”](https://www.tandfonline.com/doi/abs/10.1080/10462930108616170) — Critical analysis of the *Think Different* campaign and its use of cultural imagery.
17. [Reuters — Apple apologizes over the iPad Pro “Crush” ad](https://www.reuters.com/technology/apple-apologizes-ipad-pro-crush-ad-ad-age-says-2024-05-10/) — A cautionary case study in the gap between intended metaphor and audience interpretation.
18. [Apple's preserved Get a Mac campaign archive](https://www.apple.com/go/awards/campaign_getamac/index.html) — Examples of repeated campaign structure and concise visual comedy.
19. [Computer History Museum — Apple Macintosh 1984 commercial](https://www.computerhistory.org/timeline/1984/) — Historical context for the *1984* campaign.
20. [Apple Newsroom — Behind the Mac creator Barry Jenkins](https://www.apple.com/nz/newsroom/2019/02/behind-the-mac-creator-celebrates-his-literary-icon/) — Creator-led storytelling and the relationship between tool and practice.
21. [Apple Newsroom — MAMI Select: Filmed on iPhone](https://www.apple.com/in/newsroom/2024/04/five-filmmakers-share-their-worlds-for-mami-select-filmed-on-iphone/) — A range of authored creative approaches using iPhone capture.
22. [9to5Mac — Apple's 2023 holiday film, *Fuzzy Feelings*](https://9to5mac.com/2023/11/21/watch-apples-annual-holiday-ad-now-fuzzy-feelings-video/) — Context and access to the stop-motion/live-action film.
23. [Animator Tim Allen — *Fuzzy Feelings* behind-the-scenes credits](https://vimeo.com/888201559) — Production credits and a maker's view of the stop-motion sequence.

## Animation engineering and real-time 3D

24. [GSAP Timeline documentation](https://gsap.com/docs/v3/GSAP/Timeline/) — Master timelines, sequencing, overlaps, defaults, callbacks, and scrubbing.
25. [GSAP Easing documentation](https://gsap.com/docs/v3/Eases/) — Easing curves and how they change perceived timing and character.
26. [GSAP CustomEase](https://gsap.com/docs/v3/Eases/CustomEase/) — Authored curves via cubic Bézier strings or SVG paths.
27. [GSAP ticker](https://gsap.com/docs/v3/GSAP/gsap.ticker/) — Browser frame scheduling and lag-smoothing behavior; useful context for separating preview timing from deterministic rendering.
28. [Three.js WebGLRenderer](https://threejs.org/docs/pages/WebGLRenderer.html) — Renderer configuration, pixel ratio, output options, and capabilities.
29. [Three.js PerspectiveCamera](https://threejs.org/docs/pages/PerspectiveCamera.html) — Field of view, focal length relationships, and camera projection behavior.
30. [Three.js animation system](https://threejs.org/manual/en/animation-system.html) — Animation clips, keyframe tracks, mixers, actions, and synchronized playback.
31. [Three.js materials manual](https://threejs.org/manual/en/materials.html) — Physically based material parameters, roughness, metalness, and clearcoat.
32. [Three.js MeshPhysicalMaterial](https://threejs.org/docs/pages/MeshPhysicalMaterial.html) — Advanced surface characteristics such as anisotropy, transmission, iridescence, and clearcoat.
33. [Three.js color management](https://threejs.org/manual/en/color-management.html) — Linear working space, sRGB textures, non-color maps, and output conversion.
34. [Three.js EffectComposer](https://threejs.org/docs/pages/EffectComposer.html) — Ordering and managing post-processing passes.
35. [Three.js UnrealBloomPass](https://threejs.org/docs/pages/UnrealBloomPass.html) — Bloom thresholds, radii, strength, and implementation caveats.
36. [MDN — Animation performance and frame rate](https://developer.mozilla.org/en-US/docs/Web/Performance/Guides/Animation_performance_and_frame_rate) — Rendering cost and common causes of animation jank.
37. [MDN — CSS and JavaScript animation performance](https://developer.mozilla.org/en-US/docs/Web/Performance/Guides/CSS_JavaScript_animation_performance) — CSS animation, JavaScript animation, `requestAnimationFrame`, and performance considerations.
38. [MDN — prefers-reduced-motion](https://developer.mozilla.org/en-US/docs/Web/CSS/@media/prefers-reduced-motion) — Respecting reduced-motion preferences in interactive previews.
39. [MDN — HTMLCanvasElement.captureStream](https://developer.mozilla.org/en-US/docs/Web/API/HTMLCanvasElement/captureStream) — Real-time canvas capture and frame-rate parameters.
40. [MDN — CanvasCaptureMediaStreamTrack.requestFrame](https://developer.mozilla.org/en-US/docs/Web/API/CanvasCaptureMediaStreamTrack/requestFrame) — Manual frame request behavior and browser-compatibility caveats.
41. [MDN — MediaRecorder](https://developer.mozilla.org/en-US/docs/Web/API/MediaRecorder) — Browser media recording and codec/container configuration.
42. [Remotion — Render a video](https://www.remotion.dev/docs/render) — Code-driven rendering workflow.
43. [Remotion — Timing and trimming](https://www.remotion.dev/docs/timing) — Frame-based timing, sequence placement, trimming, and playback rate.

## How to keep this skill current

When revisiting this skill, re-check official documents and library APIs for changes. Apple updates HIG guidance over time; GSAP and Three.js API details can change; browser encoding support differs by platform. Keep the creative principles stable while updating the implementation details and source links. Do not treat a new Apple operating-system visual effect as a universal advertising style. The film's message, product, audience, and own brand identity must always lead.

---

# 24. Final directive

Create the **most intentional film that can be built within the brief's constraints**, not the most complicated scene the runtime can survive.

Make the opening specific. Make the product believable. Make light reveal form. Make motion carry intention. Make each transition connect. Make typography earn its place. Make sound support the action. Make the final frame memorable. Remove everything that exists only to make the output appear more “AI-generated,” more “cinematic,” or more expensive.

When uncertain, simplify the concept, not the craft.
