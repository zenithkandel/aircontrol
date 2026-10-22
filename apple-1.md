---
name: apple-motion-3d-ads
description: Build Apple-grade 3D product ads, scroll-driven launch pages, keynote-style motion sequences and hero animations using only code (HTML/CSS/JS, SVG, Canvas, WebGL/Three.js), with no video or image models. Contains Apple's verified design tokens (color, SF Pro type scale), motion language (springs, easing, choreography, scroll-scrubbed scenes), procedural 3D modeling, studio lighting and materials, ad structure and copywriting, plus an enforceable anti-AI-slop codex (banned gradients, tints, fonts, layouts, motion clichés). Use whenever the user wants Apple-style, premium, cinematic, keynote, product-launch, scrollytelling, 3D scroll, hero-animation or "make it look expensive" work, even if Apple is never named, and whenever generated UI or motion risks looking like generic AI output.
---

# Apple-Grade Motion and 3D Ads, Code Only

You are building a product film or launch page out of HTML, CSS and JavaScript. No video model, no image model, no stock footage. Everything on screen is geometry, light, type and time that you author.

The goal is not "Apple-flavored decoration." The goal is the *discipline* that makes Apple's work feel expensive: one idea per scene, a lot of empty space, physically plausible motion, honest materials, and zero ornament that does not carry meaning.

Read sections 0 to 3 fully before writing any code. Use sections 4 to 12 as reference while building. Section 13 is a working starter template. Section 14 is a slop linter you run before delivering.

---

## 0. How to use this skill

### 0.1 Workflow (do not skip steps)

1. **Concept Brief** (section 3.1). Write it in under 120 words before anything else.
2. **Design Plan** (token sheet): 4 to 6 named hex colors, one type family and its roles, layout concept, 3 principles. Check it against the slop codex (section 9). If any part is what you would produce for *any* product, change it and say what you changed.
3. **Pick the delivery mode** (section 8): scroll-scrubbed page, timed film, or interactive viewer.
4. **Build the stage first**: renderer, lighting, one hero object, one camera. Get a single still frame looking expensive before adding any motion.
5. **Add motion scene by scene** using the pattern catalog (section 6) and the timing tables (section 5).
6. **Run the gates** (section 12): token audit, motion audit, slop lint (section 14), performance, accessibility, screenshot review.
7. **Remove one accessory.** Delete the one effect that is least necessary. Then deliver.

### 0.2 Output contract

- One self-contained `.html` file unless the user asks otherwise. CSS and JS inline.
- External scripts only from a CDN, pinned to an exact version (never `@latest`). Prefer a release that is at least two weeks old.
- No remote images, no remote fonts you do not have a license for. All visuals are procedural (geometry, canvas, SVG, CSS).
- A deterministic time hook: `window.__renderAt(t_or_p)` so a human or script can step to any frame (section 8.4).
- A working `prefers-reduced-motion` path (section 11).
- At the end, report in 3 lines or fewer: the concept, any assumptions, and any rule from this skill you intentionally broke and why.

### 0.3 The twelve rules (if you only remember one screen of this file)

1. One subject, one idea, one camera move per scene.
2. At hero beats, at least half the frame is empty.
3. Color lives on the product. Interface chrome is neutral. One accent color, used only for action.
4. One type family. Hierarchy comes from size, weight and tracking, not from extra fonts or color.
5. Headline tracking tightens as size grows. Headline line-height is 1.0 to 1.1.
6. Nothing teleports. Every change of state is a continuous move, a morph or a deliberate cut on a held beat.
7. Entrances decelerate. Exits are shorter than entrances. Product motion settles with zero bounce.
8. Scroll (or the master clock) is the playhead. Progress maps linearly to time; easing lives *inside* each segment.
9. Materials are physical: roughness, metalness and reflections come from a studio environment, not from decorative gradients.
10. No gradient between two different hues unless it is physical light on a 3D surface or a meaningful AI-state indicator (section 9.4).
11. Motion is optional and never the only carrier of meaning.
12. If it looks like it could be any AI landing page, it is wrong. Start the design plan over.

---

## 1. Provenance and confidence (read this, it changes how you apply the numbers)

This skill is synthesized from a broad but finite research pass: Apple's Human Interface Guidelines (Motion, Typography, Materials), WWDC sessions (Animate with springs, The details of UI typography, Liquid Glass design, scroll-driven animation for the web), CSS shipped on apple.com, community teardowns of Apple's scroll-driven product pages, written analyses of Apple's keynote and product-film production, and published catalogs of AI-generated-design tells. **No video was watched.** Claims about films and keynotes come from written analyses and from general motion-design practice.

Treat every number in this file with the confidence label next to it:

| Label | Meaning |
|---|---|
| **[V]** Verified | Taken directly from Apple documentation, Apple session transcripts, or CSS served by apple.com |
| **[C]** Community-extracted | Appears consistently across multiple third-party extractions of apple.com tokens; not an Apple publication |
| **[O]** Observed / inferred | Derived from teardowns, written analyses or motion-design practice. Good starting point, tune by eye |
| **[D]** Derived | Calculated by this skill from a verified principle (for example, from critical damping) |

**Known gaps. Do not pretend otherwise:**

- Apple's exact production easing curves and internal motion tokens were **not** recovered. Every cubic-bezier below is **[O]**, a tuned approximation of the feel.
- Apple's flagship product pages (for example AirPods Pro) are reported to draw **pre-rendered image sequences to a canvas** driven by scroll position. Those frames are offline renders. You cannot reproduce photoreal CGI parity with real-time procedural geometry. You *can* reproduce the feel (restraint, lighting, timing, camera) by choosing subjects and materials that procedural 3D renders well, and by never attempting what it renders badly (fur, skin, complex organic surfaces, intricate hardware detail).
- SF Pro is licensed by Apple. Do not embed or redistribute SF font files. Use the system stack in section 4.3.
- Apple, SF Pro, the Apple logo and Apple product names are trademarks. This skill teaches a *style and discipline* to apply to the user's own product. Do not use Apple's logo, marks, imagery or verbatim copy.

---

## 2. Why Apple's work reads as better: the thesis

Apple's advantage is not a secret effect. It is ten habits applied relentlessly and consistently. Each one below has a *what it looks like*, a *how to do it in code*, and a *failure it prevents*.

### 2.1 Subtraction
- **Looks like:** one object on a void; the headline is five words; the frame is mostly empty.
- **Code:** a scene has exactly one hero element. Secondary elements are at most 30% of its visual weight (size × contrast). If you add an element, remove another.
- **Prevents:** the "everything glows and floats" look where nothing is the subject. Analyses of Apple's product films repeatedly describe *one shot = one idea* with space to breathe **[O]**.

### 2.2 Material honesty
- **Looks like:** brushed metal that catches long strips of light; glass that bends what is behind it; matte plastic that does not shine.
- **Code:** physically based materials (`MeshPhysicalMaterial`), a studio environment built from softboxes (section 7.3), correct roughness. No fake shine gradients.
- **Prevents:** plasticky 3D that screams "generated." Apple's own Liquid Glass guidance stresses lensing and specular highlights that respond to movement **[V]**, which is the same principle: light behaves like light.

### 2.3 Continuity
- **Looks like:** the camera is never perfectly still; transitions are not simple cuts; objects carry velocity across scene boundaries **[O]**.
- **Code:** a single master clock. Camera drift always on (amplitude 0.1° to 0.3°). When a scene ends, the next one starts from the previous scene's end pose, not from a reset.
- **Prevents:** slideshow feel.

### 2.4 Physical timing
- **Looks like:** things accelerate and settle like objects with mass. Gestures can be interrupted and redirected.
- **Code:** springs for anything gesture-like or UI-like (section 5.3). Decelerating curves for entrances. Critically damped smoothing for scroll. Apple's guidance: when unsure, use a spring with bounce 0; about 15% bounce does not feel very bouncy; bounce is for when you want an animation to feel physical **[V]**.
- **Prevents:** linear, mechanical or rubbery motion.

### 2.5 Typographic discipline
- **Looks like:** one family, semibold headlines, tight tracking at large sizes, generous leading on small text.
- **Code:** section 4.3. Apple's headline classes use weight 600 with tracking that *tightens as size grows* (160px at −0.015em, 64px at −0.009em, 48px at −0.003em, 40px at 0) **[V]**.
- **Prevents:** the "three weights, two fonts, gradient text" look.

### 2.6 Color is the product
- **Looks like:** monochrome stage, vivid object.
- **Code:** neutral canvases (`#000`, `#f5f5f7`, `#fff`), near-black ink `#1d1d1f`, one blue accent for action **[C]**. All other color comes from the object's materials.
- **Prevents:** rainbow UI chrome competing with the product.

### 2.7 Rhythm
- **Looks like:** deep black scenes alternating with pale neutral fields **[C]**; a held beat of pure stage between ideas; edits and motion locked to music tempo **[O]**.
- **Code:** a scene list with explicit durations, alternating stage modes, and a 400 to 700 ms "black beat" between major chapters.
- **Prevents:** a monotone stream where every moment has equal weight.

### 2.8 The viewer holds the playhead
- **Looks like:** scroll scrubs the animation forward and backward; it pauses when you stop **[V via community teardowns of the AirPods page]**.
- **Code:** one progress value per pinned section, smoothed by a short critically damped spring (section 8.1).
- **Prevents:** auto-playing animation the viewer cannot control.

### 2.9 Systemic consistency
- **Looks like:** the same radii logic, the same type recipe, the same easing family everywhere.
- **Code:** all values come from CSS variables / one JS constants object. No magic numbers in scene code.
- **Prevents:** the patchwork look of one-off effects.

### 2.10 Purposeful motion
- **Looks like:** motion explains something (what changed, what is next) and can be switched off.
- **Code:** every animation has a one-sentence justification in a comment. Make motion optional; do not use it as the only way to convey important information **[V]**. Avoid adding motion to interactions that happen frequently **[V]**.
- **Prevents:** gratuitous animation that distracts or disorients, which Apple's HIG explicitly warns against **[V]**.

---

## 3. Concept, scenes and copy

### 3.1 Concept Brief (write this first)

```
SUBJECT:    the one physical or abstract thing on screen (e.g. "a titanium slab device")
PROMISE:    the single claim (e.g. "a battery that lasts all week")
PROOF x3:   three beats that demonstrate it (one idea each)
TONE:       2 adjectives max (e.g. "calm, precise")
STAGE MODE: black void | pale studio | alternating
FORMAT:     scroll page | 15 s | 30 s | 60 s | viewer; aspect 16:9 | 9:16 | 1:1
```

If the user did not supply a product, invent a concrete one (a real object with real materials), say so in one line, and proceed. Do not build a generic "AI platform" visual.

### 3.2 Scene grammar

Every scene is a record:

```js
{ id, intent, hero, camera, type, duration, enter, exit }
// intent:   one sentence, what the viewer should understand
// hero:     the single focal element
// camera:   one move (push-in, orbit, tilt, rack)
// type:     headline text and its size tier (max 2 tiers per scene)
// duration: seconds (timed) or progress span (scroll)
// enter/exit: pattern ids from section 6
```

### 3.3 Ad structures **[O]** (these are authored templates, not Apple data)

**15 s spot (5 beats)**
| t (s) | Beat | Content |
|---|---|---|
| 0.0–2.0 | Cold open | Black. Object emerges from darkness; rim light sweeps once. No text. |
| 2.0–5.0 | Name | Product name, one line, large. Slow push-in. |
| 5.0–9.5 | Claim | One feature, one camera move, a 3 to 6 word headline. |
| 9.5–13.0 | Proof | A single specific number or a single demonstration. |
| 13.0–15.0 | Resolve | Hero pose, tagline, the user's own brand mark, availability line. |

**30 s spot (8 beats):** cold open, name, three claim/proof pairs (each ≈ 5 s), a black beat, resolve.

**60 s film (chaptered):** cold open (4 s), name (4 s), then 3 to 4 chapters of 10 to 12 s each, separated by 500 ms black beats, then a 6 s resolve.

**Scroll page:** one pinned hero (300 to 500 vh), then alternating pale/black chapters, each with one object or one idea. Short, never a feature grid.

### 3.4 Copy rules
- Headline ≤ 7 words. Prefer fragments and present tense. Name the thing, then one attribute.
- One claim per scene. No stacked superlatives ("fastest, smartest, most powerful").
- Specific beats vague: "18 hours" beats "all-day battery."
- No buzzwords ("seamless," "next-generation," "revolutionary," "supercharge," "unlock").
- Sentence case. No ALL-CAPS eyebrow above every heading. No "WORD — fragment" labels. No "A · B · C" meta strings. No arrow glyph appended to every link.
- Write your own copy. Never reproduce Apple's slogans or product copy.
- Reading time: a headline must hold on screen at least `0.6 s + 0.25 s × wordCount` after it finishes entering.

---

## 4. Visual tokens

### 4.1 Color

Three canvases drive the page: `#ffffff`, `#f5f5f7` (alternating bands) and `#000000` (hero stages). Ink is near-black `#1d1d1f`, never pure black on light. Secondary text is cool grey `#86868b`. The lone accent is blue in context-specific shades **[C]**.

```css
:root {
  /* canvases */
  --surface:            #ffffff;
  --surface-2:          #f5f5f7;   /* alternating band, footer */
  --stage:              #000000;   /* hero stage: true black, not tinted */

  /* ink */
  --ink:                #1d1d1f;   /* primary on light */
  --ink-2:              #6e6e73;   /* secondary body on light (5.0:1 on white) */
  --ink-3:              #86868b;   /* legal / tertiary (3.6:1 on white: large or non-essential only) */
  --ink-on-dark:        #f5f5f7;
  --ink-2-on-dark:      #a1a1a6;
  --divider:            #d2d2d7;

  /* the only accent family */
  --accent:             #0071e3;   /* filled buttons, focus ring */
  --accent-hover:       #0077ed;
  --accent-pressed:     #006edb;
  --link:               #0066cc;   /* inline links on light */
  --link-on-dark:       #2997ff;   /* links on black */
}
```

Rules:
- Stage black is `#000`. Do **not** substitute navy-black or tinted near-black (`#0a0e1a`, `#0b1020`, `#0f172a`, `#111`). That is a slop tell and it flattens the object.
- `#f5f5f7` is Apple's pale field; it is a *very* faint cool grey. That is the entire extent of "tint" allowed in neutrals.
- Contrast: `--ink-3` on white is about 3.6:1. Use it only for non-essential or large text. On black it is about 5.8:1.
- Color enters the frame only through (a) the product's materials, (b) the single accent for actions, (c) a real, content-bearing data color if charts are unavoidable.
- Product finish palettes (starting values **[O]**, tune against your lighting):
  - natural titanium `#8d8a84`, space black `#2a2a2c`, silver `#c9cacc`, warm gold `#c9b79c`, deep blue `#2f3a4f` (as a *material*, never as UI chrome).

### 4.2 Stage modes

| Mode | Background | Use |
|---|---|---|
| Void | `#000` | Cold open, hero reveals, glowing or emissive objects, titanium/black finishes |
| Studio | `#f5f5f7` with a faint floor falloff | Bright, friendly, light-finish products |
| Alternating | `#000` ↔ `#f5f5f7` by chapter **[C]** | Scroll pages and long films |

A stage may have a **luminance-only** radial falloff of at most ~6% to seat the object. No hue shift in the falloff.

### 4.3 Typography

**Font stack (use exactly this, it is Apple's own chain) [V]:**

```css
--font: "SF Pro Display", "SF Pro Icons", "Helvetica Neue", "Helvetica", "Arial", sans-serif;
--font-text: "SF Pro Text", "SF Pro Icons", "Helvetica Neue", "Helvetica", "Arial", sans-serif; /* sizes ≤ 19px */
```

- On Apple devices this resolves to SF Pro. Elsewhere it falls to Helvetica Neue, then Arial. **Do not embed SF files** (license). If the user supplies a licensed font file, use it as the first entry.
- Optical sizes: SF Text is designed for sizes below 20 pt and SF Display for 20 pt and above **[V]**. Use `--font-text` for ≤ 19 px, `--font` for ≥ 20 px. Smaller sizes get looser spacing and more open forms; larger sizes get tighter fit.
- Headless/Linux renders will show Arial or Liberation Sans. When capturing frames in such an environment, widen tracking by ~0.005em and re-check line breaks.
- **One family.** Hierarchy comes from size, weight (400 and 600 cover almost everything), tracking, and position. A second family is allowed only with a written 3-reason justification, and never from the banned list in section 9.5.

**Apple headline recipes (served by apple.com) [V]:**

| Role | Size | Line-height | Weight | Tracking |
|---|---|---|---|---|
| custom product headline (giant) | 160px | 1.0 | 600 | −0.015em |
| elevated headline | 64px | 1.0625 | 600 | −0.009em |
| headline | 48px | 1.08349 | 600 | −0.003em |
| reduced headline | 40px | 1.1 | 600 | 0 |
| intro (small viewport ≤ 734px breakpoint) | 19px | 1.4211 | 400 | +0.012em |
| body (recalled, verify before relying) | 17px | ~1.47 | 400 | ~−0.022em |

**The derivable rule:** tracking goes from about −0.015em at 160px to 0 at ~40px and slightly positive at small sizes. Apply it continuously:

```js
// em of letter-spacing for a given px size (headline-weight text)
const tracking = px => px >= 160 ? -0.015
  : px >= 64  ? -0.009 + (px - 64) / (160 - 64) * -0.006
  : px >= 48  ? -0.003 + (px - 48) / (64 - 48) * -0.006
  : px >= 40  ? 0      + (px - 40) / (48 - 40) * -0.003
  : px >= 20  ? 0
  : 0.012 - (px - 12) * 0.0005;  // small text opens up
```

**Fluid sizing (starting values [O]):**

```css
.h-giant   { font: 600 clamp(3.5rem, 2rem + 9vw, 10rem)/1 var(--font);      letter-spacing: -0.015em; }
.h-hero    { font: 600 clamp(2.5rem, 1.2rem + 5.4vw, 4rem)/1.0625 var(--font); letter-spacing: -0.009em; }
.h-section { font: 600 clamp(2rem, 1.4rem + 2.6vw, 3rem)/1.08 var(--font);  letter-spacing: -0.003em; }
.t-intro   { font: 400 clamp(1.0625rem, 0.95rem + 0.6vw, 1.5rem)/1.3 var(--font); }
.t-body    { font: 400 1.0625rem/1.47 var(--font-text); letter-spacing: -0.022em; }
```

Rules:
- Max 2 type tiers visible in any single scene. Max 6 distinct size/weight combinations across the whole piece.
- Line length for paragraphs ≤ 70 characters. Headlines break by meaning, not by container width (use `text-wrap: balance`).
- `font-variant-numeric: tabular-nums` for any animated number.
- Never gradient-fill text. Never outline text. Never mix an italic or alternate-color word into a headline for emphasis.
- Text is `#f5f5f7` on black and `#1d1d1f` on light. Secondary text uses `--ink-2` / `--ink-2-on-dark`.

### 4.4 Layout and spacing
- 8px base grid. Section vertical padding 96 to 160px desktop, 64 to 96px mobile **[O]**.
- Text column ≈ 980px max, centered or left-aligned to one shared axis **[O]**. Imagery may go full-bleed.
- Alignment: pick one axis (centered *or* left) per piece. Do not mix.
- Breakpoints to design for: ≤ 734px (phone), ≤ 1068px (tablet, recalled), above that desktop. 734px is **[V]** from apple.com CSS.
- Safe margins for film layouts: action-safe 5%, title-safe 10% on every side.

### 4.5 Shape, radius, elevation
- **Concentric radii:** inner radius = outer radius − padding. Corners of nested shapes must share a center. (Apple's Liquid Glass principles call out concentric shapes **[V]**.)
- One radius *logic*, not one radius value: a button, a card and a media tile can differ, but each follows the rule above relative to its container.
- Primary CTA: pill (`border-radius: 980px`) filled `--accent`, white text, 17px/400 or 600 **[C]**. Secondary: text link with chevron-free underline-on-hover.
- **No drop shadows on flat UI.** Separation comes from surface contrast (`#fff` on `#f5f5f7`). Physical shadows exist only on 3D objects and are contact-grounded (section 7.7).
- No borders on cards unless they encode structure (e.g., a comparison table).

### 4.6 Translucent materials (Liquid Glass-style), used correctly
Apple's guidance: glass is reserved for the navigation / control layer floating above content; content stays at the base **[V]**. It materializes in and out by modulating lensing and light, not by plain fading **[V]**. As glass gets larger it behaves thicker: deeper shadows, stronger refraction, softer scatter **[V]**.

Web approximation:

```css
.glass {
  background: rgba(255,255,255,0.08);
  backdrop-filter: blur(24px) saturate(1.5);
  -webkit-backdrop-filter: blur(24px) saturate(1.5);
  box-shadow: inset 0 1px 0 rgba(255,255,255,0.35),      /* specular top edge */
              inset 0 0 0 1px rgba(255,255,255,0.08);    /* rim */
}
@media (prefers-reduced-transparency: reduce) { .glass { background: rgba(28,28,30,0.92); backdrop-filter: none; } }
```
Use at most **one** glass element per scene, and only if it is a control layer. Glass behind body text must keep ≥ 4.5:1 contrast.

---

## 5. Motion system

### 5.1 Principles from Apple's HIG **[V]**
- Use motion to communicate: show what changed, what will happen, what can be done next.
- Add motion purposefully; do not add motion for its own sake.
- Make motion optional; reduce or eliminate it when Reduce Motion is on.
- Prefer realistic motion; motion that defies physical laws disorients.
- Avoid adding motion to frequent interactions.
- Do not rely on motion as the sole carrier of important information.
- Spatial/immersive contexts: avoid motion at the edges of the field of view.

### 5.2 Duration tiers **[O]**

| Tier | Range | Use |
|---|---|---|
| Instant | 80–150 ms | press feedback, focus ring |
| Micro | 150–250 ms | hover, toggle, chip state |
| UI | 300–500 ms | panels, tabs, small element moves |
| Scene element | 700–1400 ms | headline in, object reveal, camera settle |
| Camera / chapter | 2–6 s | pushes, orbits, exploded-view moves |
| Black beat | 400–700 ms | rhythm break between ideas |

Rules: exits take 60 to 70% of the entrance duration. Never exceed 1.4 s for a single element entrance. Total stagger across a group ≤ 600 ms.

### 5.3 Springs (the core tool)

Apple's spring model has two perceptual parameters, **duration** and **bounce**; bounce ranges −1 to 1; when unsure use bounce 0 **[V]**. Stiffness is `(2π / duration)²` **[V]**. With mass = 1, damping follows from critical damping: `c = 4π(1 − bounce)/duration` for bounce ≥ 0 **[D]**, and `c = 4π / (duration + 4π·bounce)` for bounce < 0 (as published; verify against WWDC23 session 10158 if exact parity matters).

> Note: some transcripts of that session render the damping formula garbled. The form above is derived from critical damping, `c_crit = 2·sqrt(k·m)`, and reduces to critical damping at bounce 0.

```js
export function springParams(duration, bounce = 0) {
  const k = Math.pow((2 * Math.PI) / duration, 2);
  const c = bounce >= 0
    ? (4 * Math.PI * (1 - bounce)) / duration
    : (4 * Math.PI) / (duration + 4 * Math.PI * bounce);
  return { k, c };
}

export class Spring {
  constructor(value = 0, { duration = 0.5, bounce = 0 } = {}) {
    this.x = value; this.target = value; this.v = 0;
    this.configure({ duration, bounce });
  }
  configure({ duration, bounce = 0 }) { Object.assign(this, springParams(duration, bounce)); }
  set(target) { this.target = target; }
  snap(value) { this.x = this.target = value; this.v = 0; }
  step(dt) {                                   // semi-implicit Euler, fixed substeps, mass = 1
    let t = Math.min(dt, 0.1);
    const h = 1 / 240;
    while (t > 0) {
      const s = Math.min(h, t);
      this.v += (-this.k * (this.x - this.target) - this.c * this.v) * s;
      this.x += this.v * s;
      t -= s;
    }
    return this.x;
  }
  get settled() { return Math.abs(this.x - this.target) < 1e-4 && Math.abs(this.v) < 1e-3; }
}
```

**Which spring for what [O]:**

| Situation | duration | bounce |
|---|---|---|
| Scroll/progress smoothing | 0.12–0.25 s | 0 |
| Product object settling | 0.8–1.4 s | 0 |
| UI panel / sheet | 0.35–0.5 s | 0 to 0.1 |
| Playful control released after a flick | 0.4–0.6 s | 0.15–0.3 |
| Anything bigger than ~40% of the viewport | ≥ 0.8 s | 0 |

Gesture principles (Apple's *Designing Fluid Interfaces* talk, via secondary summaries **[O]**): respond instantly to input; motion starts from the *current* on-screen value and inherits velocity; animations are interruptible and redirectable; add bounce only when the gesture itself carried momentum; soft boundaries (rubber-banding) instead of hard stops. Momentum projection (where a flick will land):

```js
// velocity in units/second; decelerationRate ≈ 0.998 (normal) or 0.99 (fast)
const project = (v, rate = 0.998) => (v / 1000) * rate / (1 - rate);
```

### 5.4 Easing library **[O]** (approximations; Apple's exact curves not recovered)

```css
:root {
  --ease-out:        cubic-bezier(0.22, 1, 0.36, 1);   /* decelerate: default for entrances */
  --ease-out-strong: cubic-bezier(0.16, 1, 0.30, 1);   /* long tail: hero reveals */
  --ease-in-out:     cubic-bezier(0.65, 0, 0.35, 1);   /* symmetrical: camera moves between poses */
  --ease-sheet:      cubic-bezier(0.32, 0.72, 0, 1);   /* iOS-sheet-like: panels, drawers */
  --ease-exit:       cubic-bezier(0.4, 0, 1, 1);       /* accelerate: exits */
}
```

Rules:
- **Scroll-scrubbed values are mapped linearly from progress**; easing is applied to the *segment-local* value (`ease(seg(p, a, b))`), never to the playhead itself. Otherwise the object feels detached from the finger.
- Never use plain `linear` for anything that should look physical, except the playhead itself and constant-speed loops (turntable, rotating glow).
- Never use `ease-in` alone for entrances. Never use elastic or back-overshoot curves on product objects.

Reference implementations (use these in JS-driven scenes):

```js
export const clamp01 = x => Math.min(1, Math.max(0, x));
export const lerp = (a, b, t) => a + (b - a) * t;
export const seg = (p, a, b) => clamp01((p - a) / (b - a));       // local progress of p within [a,b]
export const damp = (cur, target, lambda, dt) => lerp(cur, target, 1 - Math.exp(-lambda * dt)); // frame-rate independent

export function cubicBezier(x1, y1, x2, y2) {
  const cx = 3 * x1, bx = 3 * (x2 - x1) - cx, ax = 1 - cx - bx;
  const cy = 3 * y1, by = 3 * (y2 - y1) - cy, ay = 1 - cy - by;
  const X = t => ((ax * t + bx) * t + cx) * t;
  const Y = t => ((ay * t + by) * t + cy) * t;
  const dX = t => (3 * ax * t + 2 * bx) * t + cx;
  return x => {
    if (x <= 0) return 0; if (x >= 1) return 1;
    let t = x;
    for (let i = 0; i < 8; i++) {
      const e = X(t) - x;
      if (Math.abs(e) < 1e-6) return Y(t);
      const d = dX(t); if (Math.abs(d) < 1e-6) break;
      t -= e / d;
    }
    let lo = 0, hi = 1; t = x;
    for (let i = 0; i < 24; i++) { const e = X(t); if (Math.abs(e - x) < 1e-6) break; if (x > e) lo = t; else hi = t; t = (lo + hi) / 2; }
    return Y(t);
  };
}
export const E = {
  out:       cubicBezier(0.22, 1, 0.36, 1),
  outStrong: cubicBezier(0.16, 1, 0.30, 1),
  inOut:     cubicBezier(0.65, 0, 0.35, 1),
  sheet:     cubicBezier(0.32, 0.72, 0, 1),
  exit:      cubicBezier(0.4, 0, 1, 1),
};
```

### 5.5 Choreography rules
1. **One hero motion per scene.** Everything else moves at ≤ 30% of its amplitude or speed.
2. **Mass sets speed.** Larger or heavier objects move slower and with longer decelerating tails.
3. **Entrances decelerate; exits accelerate and are shorter.**
4. **Text entrance:** opacity 0→1 plus a translate of ≤ 24px (or a 6→0 px blur) over 700 to 1000 ms with `--ease-out-strong`. Object arrives first; text follows 300 to 500 ms later.
5. **Stagger** by reading order, 60 to 120 ms per line or word group, total ≤ 600 ms.
6. **Max three property types** animating on one element at once (for example opacity + translate + blur).
7. **Settle without bounce** for products and headlines.
8. **Carry velocity** across scene boundaries: the first frame of scene N+1 continues the last frame of scene N.
9. **Parallax depth ratios:** foreground 1.0, mid 0.6, background 0.3 of the scroll/camera delta.
10. **Idle life:** hero objects get a micro-drift (0.1° to 0.3° rotation, ±0.5% position, period 8 to 14 s) so the frame never freezes. Disabled under reduced motion.
11. **Hold beats.** After a headline lands, hold still for the reading time in 3.4. Silence is part of the animation.
12. **Never animate layout properties** (`width`, `height`, `top`, `left`, `margin`) on the DOM. Use `transform` and `opacity` (and, sparingly, `filter`, `clip-path`).

### 5.6 Numeric cheat sheet **[O]**

| Quantity | Value |
|---|---|
| Text slide distance | 12–24 px (never > 40) |
| Hero scale-in | 0.96 → 1.0 |
| Object reveal exposure ramp | 0 → 1 over 1.0–1.4 s |
| Camera push-in per scene | 2–6% distance change |
| Camera FOV (hero product) | 18–28° vertical |
| Camera FOV (environment / wide) | 35–50° |
| Turntable per chapter | 25–90° (never a full spin unless the scene is about the spin) |
| Blur-in | 8 → 0 px |
| Parallax ratios | 1.0 / 0.6 / 0.3 |
| Opacity ramp for scroll-read text | 0.28 → 1.0 per word |
| Black beat | 400–700 ms |
| Count-up numbers | 1.2–1.8 s, `--ease-out-strong` |

---