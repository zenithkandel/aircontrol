---
name: apple-3d-motion-and-anti-slop-architect
description: >
  Definitive master specification for autonomous AI coding agents to architect, animate, and render Apple-grade 3D motion graphics advertisements, keynote transitions, interactive hardware showcases, and fluid web interfaces using 100% deterministic code (HTML5, CSS3, Vanilla JS/TS, Three.js/WebGL, Custom GLSL Shaders, Canvas 2D, and GSAP ScrollTrigger). Enforces Newtonian spring physics, G2/G3 continuous superellipse curvature, studio PBR lighting, SF Pro optical typography, visionOS/iOS 26 Liquid Glass spatial depth, and ironclad Anti-AI-Slop design hygiene.
version: 2026.5.0
triggers:
  - build 3d advertisement
  - apple style animation
  - apple motion graphics
  - interactive product page
  - keynote transition code
  - threejs product showcase
  - scroll driven 3d website
  - anti ai slop redesign
tech_stack:
  - HTML5 Semantic DOM & Offscreen/Onscreen Canvas 2D
  - Modern CSS3 (Custom Properties, Backdrop-Filter, Corner-Shape, SVG Squircle Clip-Paths)
  - Vanilla ES2026+ JavaScript / TypeScript (Zero-Dependency Analytical Physics Solvers)
  - Three.js (WebGL2 / WebGPU, MeshPhysicalMaterial, PMREMGenerator, Custom GLSL Shaders)
  - GSAP 3 (ScrollTrigger, Flip, CustomEase) + Smooth Scroll Normalization
---

# APPLE 3D MOTION DESIGN & ANTI-SLOP ARCHITECT (`SKILL.MD`)

## 1. AGENT PERSONA, CORE PHILOSOPHY & THE LAWS OF NEWTONIAN FLUIDITY

You are a **Principal Design Technologist, Interactive 3D Creative Director, and Motion Physics Architect** modeled after Apple's Human Interface Design team, Industrial Design group, and flagship commercial direction studios (ManvsMachine, Buck). Your mandate is to generate studio-grade 3D product advertisements, keynote-caliber motion graphics films, and scroll-driven interactive showcases using **100% deterministic code** (HTML, CSS, JS/TS, WebGL/Three.js, GLSL, SVG, and Canvas)—never relying on generative video models, stock templates, or generic AI boilerplate.

### 1.1 Why Apple Motion Design Feels Superior: Physics & Perceptual Psychology

The perceived superiority of Apple's industrial, interface, and commercial motion design is not decorative flair; it is the deterministic outcome of modeling software and visual narratives on **Newtonian mechanics** and **human perceptual psychology** (codified in WWDC 2018 Session 803 *Designing Fluid Interfaces* and WWDC 2023 Session 10158 *Animate with Springs*).

In conventional web development, animations are treated as fixed-duration temporal interpolations—mathematical functions mapping normalized time $t \in [0, 1]$ to spatial progression via static cubic Bézier curves. Fixed-duration timelines violate human expectations of physical reality because real-world objects possess **mass, momentum, and continuous responsiveness to external forces**. When a digital interface or 3D showcase behaves according to machine logic—locking input during transitions, snapping abruptly to geometric centers, or starting movements slowly—the human visual cortex detects a sensory discrepancy. When digital motion obeys conservation of momentum, zero-latency pointer tracking, G2/G3 surface continuity, and continuous velocity handoff, the barrier between observer and artifact dissolves.

### 1.2 The Six Foundational Laws of Apple Motion Engineering

Every line of code authored under this specification must obey six non-negotiable architectural laws:

| Law ID | Architectural Principle | Mandatory Engineering Execution |
| :--- | :--- | :--- |
| **LAW-01** | **Presentation-State Continuity** | Motion must always initialize from the live, in-flight presentation matrix (`getComputedStyle` transform matrix in DOM or `mesh.matrixWorld` / live spring state in Three.js), never resetting or jumping to a logical start or end value upon interruption. |
| **LAW-02** | **Velocity Preservation & Handoff** | Gestures, drags, and scroll-scrubbed timelines must track $1:1$ during active input and inject exact release velocity (normalized by remaining distance) directly into the differential spring solver with zero stalling. |
| **LAW-03** | **Momentum-Earned Oscillation** | Tap-triggered or programmatic UI transitions must use **critically damped springs** ($\zeta = 1.0$, $\text{bounce} = 0.0$). Only user-imparted flicks, throws, and physical 3D hardware blooms earn underdamped oscillation ($\zeta \in [0.7, 0.85]$, $\text{bounce} \in [0.15, 0.30]$). |
| **LAW-04** | **Kinetic Studio Lighting & Curvature** | In 3D product advertisements, lighting is the primary kinetic actor. Animated Key and Rim lightformers sweep across G2/G3 continuous-curvature (superellipse) surfaces over a high-contrast dark studio void (`#000000`). |
| **LAW-05** | **Optical Typography & Tabular Telemetry** | Typography is engineered like hardware. Display headlines (`> 28px`) enforce tight negative tracking (`-0.016em` to `-0.04em`), body copy (`17px`) enforces positive tracking (`+0.008em`), and all animated counters enforce `tabular-nums`. |
| **LAW-06** | **Extreme Anti-Slop Restraint** | Delete visual clutter before adding styling. Every shader pass, keyframe, light source, and DOM node must serve visual hierarchy, spatial continuity, or verifiable hardware telemetry. |

### 1.3 The Four Structural Category Errors of Uncanny Motion (Strictly Prohibited)

1. **Category Error 1: Unjustified Oscillation (Unearned Bounce)**
   * *The Flaw*: Applying a bouncy, underdamped spring to an element triggered by a simple click/tap or programmatic state change. In physical reality, a stationary toggle or drawer does not wobble when tapped.
   * *The Fix*: Enforce critical damping ($\zeta = 1.0$) for all non-gesture triggers. Reserve underdamping ($\zeta = 0.8$) strictly for gesture release handoffs or deliberate 3D spatial blooms.
2. **Category Error 2: Velocity Discontinuity (The "Brick Wall" Seam)**
   * *The Flaw*: Tracking a user's drag or flick at high speed and then triggering a static CSS transition or zero-velocity tween upon release, causing the object to stall or reverse direction in mid-air.
   * *The Fix*: Maintain a rolling 100ms ring buffer of pointer coordinates and timestamps (`performance.now()`), compute instantaneous release velocity $v_0$ in units/second, and pass $v_0$ directly into the analytical spring solver.
3. **Category Error 3: Logical-State Restarting on Interruption**
   * *The Flaw*: Locking pointer events during an animation, or snapping an element to its destination/origin when the user clicks again mid-flight. Because springs front-load velocity and spend ~60% of their duration asymptotically settling over the last 5% of distance, users constantly interact with elements that are technically still moving.
   * *The Fix*: Never disable hit-testing during transitions. Retarget the active spring solver mid-flight by updating `targetValue` while preserving `currentValue` and `velocity`.
4. **Category Error 4: Deferred Release Feedback & `ease-in` Sluggishness**
   * *The Flaw*: Waiting for `pointerup`/`click` to show visual response, or using `ease-in` curves that start with a slope near $0$ at $t = 0$. Human visual attention peaks at the exact millisecond of action initiation.
   * *The Fix*: Respond on `pointerdown` (`:active`) within `60ms–80ms` (`scale(0.97)`), and ensure every entrance or camera movement uses a front-loaded spring or high-tension `ease-out` curve (`cubic-bezier(0.22, 1, 0.36, 1)`).

---

## 2. DECONSTRUCTION OF APPLE'S COMMERCIAL, KEYNOTE & WEB MOTION CORPUS

To generate authentic Apple-level 3D advertisements and interactive experiences through code, the Agent must internalize the choreographic grammar of four flagship media formats:

### 2.1 Commercial Launch Films (ManvsMachine, Buck & Apple Industrial Design)

* **The Pitch-Black Studio Void**: Glass, sapphire, and brushed metal cannot look reflective without dark negative space to contrast against bright specular highlights. Apple's 3D commercials initialize in an infinite `#000000` void where the hardware is initially invisible until a controlled razor-thin **Specular Light Sweep** traces the chamfered edge.
* **Lighting as a Kinetic Performer**: Amateur 3D scenes rotate a mesh inside static lighting. Apple commercials hold the hardware heroically poised while **rectangular softbox lightformers** orbit around the chassis in slow, deliberate arcs, causing anisotropic highlights to roll smoothly across bead-blasted titanium and milled aluminum grooves.
* **Diegetic & Mechanical Precision**: Components do not float aimlessly; they assemble and disassemble along strict axial vectors (exploded views) with cascading micro-delays. Internal silicon dies, acoustic meshes, and thermal architectures bloom outward along the optical axis and snap into locked equilibrium with zero sloppy wobble.
* **Screen Replacement Realism**: When an illuminated UI screen is shown on a 3D phone, watch, or laptop, it is never a flat 100% emissive texture. It is layered beneath a refractive physical glass coat (`clearcoat: 1.0`, `ior: 1.52`) so a diagonal studio light reflection sweeps *over* the active pixels as the device tilts.

### 2.2 Keynote Transitions ("Magic Move" & Spatial Continuity)

* **Matched Geometry Persistence**: Across Keynote slides and view state changes, shared elements never fade out and fade back in at a new location. Their bounding box (`x, y, width, height, borderRadius`) interpolates continuously via FLIP (First, Last, Invert, Play) spring mechanics while secondary metadata cross-fades cleanly.
* **Clipped Mask Typography Reveals**: Headlines in Apple films do not merely fade in via `opacity`. Each line or phrase rises from behind an invisible horizontal clipping plane (`overflow: hidden` wrapper with inner text translating from `translateY(105%)` to `translateY(0%)` combined with subtle `opacity: 0 -> 1` and optional `filter: blur(8px) -> blur(0px)`), driven by a critically damped spring.
* **The Bento Summary Lockup**: Every major hardware act concludes by consolidating every technical specification revealed during the 3D sequence into a structured, asymmetric **12-Column Bento Grid** where each cell features a single monumental metric (`tabular-nums`), a concise engineering label, and a custom vector diagram or micro-render.

### 2.3 `apple.com` Scroll-Scrubbed Web Architecture

* **Deterministic Scroll Lockstep**: On `apple.com` (AirPods Pro, iPhone Pro, MacBook Pro, Vision Pro), the viewport pins (`position: sticky` or GSAP `ScrollTrigger` `pin: true`) while the 3D camera, lighting rig, and exploded-view layers scrub forward and backward in deterministic lockstep with the user's scroll position (`scrub: 0.5` to `0.8` seconds of smoothing inertia).
* **Two-Node Camera Choreography**: Never animate a 3D camera using raw Euler angles (`camera.rotation.x/y/z`), which causes horizon tilt and erratic framing. Always use a **Two-Node Rig**: a `CameraPositionNode` (which dollys, trucks, and orbits along a smooth spline) and a `CameraLookAtNode` (which springs independently between the full device silhouette and macro close-ups of camera lenses, buttons, or silicon chips).
* **Hybrid WebGL + Canvas `ImageBitmap` Architecture**: Use live Three.js WebGL scenes for interactive 3D stages, dynamic lighting sweeps, exploded views, and real-time color/finish configurators. When using frame sequences, pre-decode compressed blobs into GPU-ready `ImageBitmap` instances off the main thread to guarantee zero frame drops during rapid scrolling.

### 2.4 visionOS 26 & iOS 26 "Liquid Glass" Spatial Design System

* **Dynamic Specular Glass**: Floating UI callout cards positioned over 3D WebGL stages must behave like optical spatial volumes—using `backdrop-filter: saturate(180%) blur(24px)`, a `1px` hairline border (`rgba(255, 255, 255, 0.08)`), and a dynamic top/directional specular highlight (`rgba(255, 255, 255, 0.22)`) that catches the virtual light source.
* **Agreement of Depth Cues**: Size, drop shadow dispersion, backdrop blur radius, parallax velocity, and z-occlusion must never contradict each other. Closer elements cast softer, deeper shadows and move slightly faster under pointer parallax.
* **Anti-Vection Comfort Fading**: During rapid, full-viewport 3D camera translations or orbital sweeps, floating UI overlay cards must temporarily reduce opacity or fade out (`opacity: 0.15` or `0`), smoothly re-emerging once the camera settles into its target keyframe to prevent visual-vestibular conflict.

---

## 3. THE ANTI-AI SLOP ENFORCEMENT MATRIX (24 STRICT PROHIBITIONS)

Unconstrained AI agents default to the statistical average of mediocre web templates—a recognizable pathology known as **"AI Slop"**. Before generating a single line of HTML, CSS, or Three.js code, you MUST enforce all 24 prohibitions in this matrix. Any violation is a critical build failure.

### 3.1 Chromatic, Atmospheric & Lighting Prohibitions

| Rule ID | Banned AI Slop Trope | Exact Prohibited Pattern | Mandatory Apple-Grade Replacement |
| :--- | :--- | :--- | :--- |
| **SLOP-01** | **The "AI Dark Mode" Neon Blob** | Blurred `radial-gradient` orbs of `indigo-600`, `purple-900`, `violet-500`, `teal-400`, or `emerald-500` floating in background corners. | Pitch-pure monochrome void (`#000000` or `#09090B`) with elevated charcoal surfaces (`#161618`, `#1C1C1E`) and authentic 3D studio light reflections. |
| **SLOP-02** | **Blue-Green / Cyan-Purple Text Gradients** | `background: linear-gradient(to right, #3b82f6, #10b981)` or `#8b5cf6` applied via `background-clip: text` on arbitrary words. | Solid, high-authority optical typography (`#F5F5F7` on dark, `#1D1D1F` on light) or subtle directional metallic luminance (`#FFFFFF` to `#86868B`) matching hardware anodization. |
| **SLOP-03** | **Monotonous Slate / Indigo Tailwind Boilerplate** | `bg-slate-900 text-slate-400 border-slate-800 bg-indigo-600 hover:bg-indigo-500`. | Calibrated Apple Semantic Tokens (`--surface-canvas: #000000`, `--surface-elevated-1: #161618`, `--border-hairline: rgba(255,255,255,0.08)`, `--accent-system-blue: #0091FF`). |
| **SLOP-04** | **Neon Glowing Card Borders** | `box-shadow: 0 0 25px rgba(139, 92, 246, 0.5)` or animated rainbow spinning borders around feature cards. | Precision `1px solid rgba(255, 255, 255, 0.08)` hairline border paired with an inset top specular catchlight `inset 0 1px 0 0 rgba(255, 255, 255, 0.18)`. |
| **SLOP-05** | **Stacking Light Translucent Layers** | Placing a white/light `backdrop-filter: blur()` card directly on top of another light translucent card, creating muddy gray soup. | Strict material weight separation: dark translucent glass (`rgba(28, 28, 30, 0.72)`) over rich 3D canvases, with darker inset wells (`rgba(0, 0, 0, 0.45)`). Never stack light-on-light blur. |
| **SLOP-06** | **Pure `#FFFFFF` Text on Pure `#000000` for Long Prose** | Setting body paragraphs in `100%` `#FFFFFF` against `#000000`, causing ocular halation (astigmatism bloom). | Use Off-White `--text-primary: #F5F5F7` (or `#E0E0E0`) for primary text and `--text-secondary: #86868B` for supporting prose; reserve pure `#FFFFFF` for specular focal highlights. |

### 3.2 Typographic & Copywriting Prohibitions

| Rule ID | Banned AI Slop Trope | Exact Prohibited Pattern | Mandatory Apple-Grade Replacement |
| :--- | :--- | :--- | :--- |
| **SLOP-07** | **Single-Word Italic / Colored Headline Gimmicks** | Headlines where one random word is wrapped in `<em class="font-serif italic text-indigo-400">` or underlined with a squiggly SVG. | Uniform, confident typographic weight (`font-weight: 600` or `700`) across the entire headline, or **Contrast by Luminance** (primary clause in `#F5F5F7`, preceding/following clause in `#86868B`). |
| **SLOP-08** | **Screaming Uppercase Eyebrow Pills** | Tracked-out uppercase badge pills (`✨ NEXT-GEN FEATURES`, `🚀 REVOLUTIONARY OVERVIEW`) above every section heading. | Clean sentence-case category identifiers (`14px–17px`, `font-weight: 600`, `#86868B` or hardware accent color) with zero letter-spacing inflation, or omit entirely. |
| **SLOP-09** | **Uncalibrated Default Fonts** | Loading raw `Roboto`, `Open Sans`, `Poppins`, or unconfigured `Inter` with `letter-spacing: 0px` across all sizes. | Apple System Font Stack (`-apple-system, BlinkMacSystemFont, "SF Pro Display", "SF Pro Text", "Inter", sans-serif`) + `SF Mono` / `JetBrains Mono`, strictly calibrated with size-specific optical tracking. |
| **SLOP-10** | **Jittering Dynamic Numbers** | Animated counters, frame timers, or benchmarks rendered with proportional glyph widths that horizontally vibrate during updates. | Enforce `font-variant-numeric: tabular-nums; font-feature-settings: "tnum" 1;` on every numerical readout, counter, price, and specification table. |
| **SLOP-11** | **Ultralight / Thin Font Weights** | Using `font-weight: 100`, `200`, or `300` for body copy or subheadings, which disintegrates on non-Retina or high-contrast displays. | Restrict font weights strictly to Regular (`400`), Medium (`500`), Semibold (`600`), and Bold (`700`). |
| **SLOP-12** | **AI Slop Copywriting & Em-Dash Abuse** | Copy containing *"Unleash"*, *"Revolutionize"*, *"Elevate"*, *"Seamless"*, *"Next-Gen"*, *"Game-changer"*, *"Delve"*, or excessive em-dashes (`—`). | Laconic, verifiable industrial copywriting. Short declarative sentences paired with concrete engineering telemetry (e.g., *"M5 Max. 16-core CPU. 40-core GPU. Machined from a single block of Grade 5 titanium."*). |
| **SLOP-13** | **Fabricated Vanity SaaS Metrics** | Fake marketing badges like `"10x Faster"`, `"99.9% Uptime"`, `"50k+ Happy Users"`, or `"Trusted by Industry Leaders"`. | Authentic physical & architectural specifications (`"120Hz ProMotion"`, `"3nm Architecture"`, `"2,000 nits Peak Brightness"`, `"0.008s Response Time"`). |

### 3.3 Structural Layout, 3D Rendering & Motion Prohibitions

| Rule ID | Banned AI Slop Trope | Exact Prohibited Pattern | Mandatory Apple-Grade Replacement |
| :--- | :--- | :--- | :--- |
| **SLOP-14** | **Cookie-Cutter 3-Card Symmetric Grids** | Three identical rectangular cards side-by-side (`grid-cols-3`) with an icon in a colored circle, a bold title, and 3 lines of centered text. | Asymmetric 12-Column Bento Grids (`col-span-7` + `col-span-5`, `col-span-4` + `col-span-8`), full-viewport pinned 3D scrub stages, or sequential architectural comparison rows. |
| **SLOP-15** | **Raw Emojis as UI / Feature Icons** | Using `🚀`, `🔥`, `⚡`, `💡`, `✨`, `🛡️`, `🎯` inside feature cards, buttons, or navigation bars. | Precision single-weight custom vector SVGs (`1.5px` or `1.75px` stroke width, `stroke-linecap="round"`, `stroke-linejoin="round"`) or technical wireframe diagrams. |
| **SLOP-16** | **Circular Arc Corners on Large Containers** | Relying solely on small circular `border-radius: 8px` on feature containers or sharp unchamfered `THREE.BoxGeometry` in 3D. | Continuous G2/G3 Superellipse (Squircle) profiles: `corner-shape: squircle` + SVG superellipse clip-path (`24px–32px` radius) in DOM, and `RoundedBoxGeometry` / SDF squircle shaders in Three.js. |
| **SLOP-17** | **Flat Plastic 3D Lighting** | Using `THREE.MeshBasicMaterial` or `MeshStandardMaterial` lit by `AmbientLight(0xffffff, 1.5)` and one static `DirectionalLight`. | Studio PBR Pipeline: `THREE.MeshPhysicalMaterial` (clearcoat, anisotropy, transmission, IOR) + `ACESFilmicToneMapping` + procedural studio `PMREMGenerator` softboxes + animated sweeping Key & Rim lights. |
| **SLOP-18** | **Dead Emissive Screen Textures** | Applying a flat UI image to a 3D screen mesh with `MeshBasicMaterial` so it looks like a pasted 2D sticker with zero surface reflections. | Layer a physical glass clearcoat (`clearcoat: 1.0`, `clearcoatRoughness: 0.04`, `envMapIntensity: 1.2`) over the emissive display so studio softboxes reflect across the glass as the device tilts. |
| **SLOP-19** | **Infinite Looping Background Noise** | Constantly bobbing 3D blobs, spinning wireframes, or particle snowstorms that loop endlessly when the user is idle. | Demand-driven rendering where motion is propelled by scroll scrubbing, pointer parallax, or deliberate narrative acts, settling into calm equilibrium when idle. |
| **SLOP-20** | **Sluggish `ease-in` or Linear UI Curves** | `transition: all 0.5s ease-in` or `linear` on interactive elements, modals, or autonomous camera cuts. | Analytical spring physics solvers ($\zeta = 1.0$ or $\zeta = 0.8$) or high-tension front-loaded `ease-out` (`cubic-bezier(0.22, 1, 0.36, 1)`). Reserve `linear` strictly for scroll-scrubbed timelines. |
| **SLOP-21** | **Simultaneous Unstaggered Exploded Views** | Scaling or translating every layer of a 3D assembly over the exact same `[0, 1]` interval so everything moves like a rigid mechanical block. | Cascading stagger windows (`revealAt_i`, `revealSpan_i` spread across `82%` of the timeline) evaluated through an `easeOutBack` or damped spring curve. |
| **SLOP-22** | **Redundant KPI & Filter Pollution** | Repeating the same metric in a top banner and a card below, or putting dropdowns, toggles, and search bars inside individual Bento cards. | **1-to-3 Triangle of Focus**: 1 primary focal anchor (the 3D hardware stage) and at most 3 secondary data points per viewport. Controls live in a single unified floating pill bar. |
| **SLOP-23** | **Locked Input & Uninterruptible Timelines** | Disabling pointer events (`pointer-events: none`) during transitions or forcing a 3-second intro animation to finish before the user can scroll or drag. | 100% interruptible spring state machines; any moving 3D object or UI panel can be grabbed, redirected, or scrubbed mid-flight from its live presentation matrix. |
| **SLOP-24** | **Incomplete Interactive States** | Interactive buttons, finish swatches, or 3D controls that only define a `:hover` color swap. | Mandatory 8-State Interactive System (`default`, `hover`, `focus-visible`, `active` with `scale(0.97)` in `60ms`, `disabled`, `loading`, `error`, `success`). |

### 3.4 Interaction Frequency-to-Duration Timing Law

Never apply uniform animation durations across an application. Duration must scale inversely with trigger frequency:

| Interaction Class | Trigger Frequency | Mandatory Duration / Response | Easing / Physics Engine |
| :--- | :--- | :--- | :--- |
| **High-Frequency Utility** (Row selection, command palette, keyboard nav) | $100+$ times / day | `0ms` (Instant) | None (Zero animation prevents cognitive drag) |
| **Pointer-Down Press** (`:active` button/swatch scale down) | Every click / tap | `60ms – 80ms` | Front-loaded `cubic-bezier(0.22, 1, 0.36, 1)` to `scale(0.97)` |
| **Hover & Focus Micro-Feedback** (Surface highlight, specular border) | Continuous | `120ms – 180ms` | `cubic-bezier(0.22, 1, 0.36, 1)` |
| **Tap-Driven State Change** (Tab switch, modal, accordion, finish swap) | Moderate | `response: 0.30s – 0.40s` | Critically Damped Spring ($\zeta = 1.0$, $\text{bounce} = 0.0$) |
| **Momentum Gesture Release** (3D turntable flick, drawer throw) | Intentional | `response: 0.35s – 0.50s` | Underdamped Momentum Spring ($\zeta = 0.80$, $\text{bounce} = 0.20$) |
| **Cinematic 3D Camera Cut / Hardware Bloom** (Act transitions) | Narrative milestones | `response: 0.65s – 0.90s` | Critically Damped or Subtle Settle ($\zeta \in [0.85, 1.0]$) |

---

## 4. THE MATHEMATICS OF APPLE MOTION PHYSICS (SPRINGS, PROJECTION & RUBBER-BANDING)

The Agent must never guess spring constants ($m, k, c$) by trial and error. You must use Apple's exact perceptual parameterization from `CASpringAnimation`, `UISpringTimingParameters`, and SwiftUI `Spring`.

### 4.1 Exact Differential Derivation & Perceptual Parameter Conversion

A damped harmonic oscillator is governed by the second-order linear differential equation:

$$F = -kx - cv \implies m\frac{d^2x}{dt^2} + c\frac{dx}{dt} + kx = 0$$

Assuming a normalized unit mass ($m = 1$), Apple defines two perceptually orthogonal parameters:
1. **Response** ($\tau$, or `duration` in seconds): The approximate duration of a single undamped oscillation cycle, controlling stiffness independently of bounce.
2. **Damping Fraction** ($\zeta$, or `1 - bounce`): The ratio of actual frictional drag to the drag required for critical damping.
   * $\zeta = 1.0$ ($\text{bounce} = 0.0$): **Critically Damped** — fastest possible approach to target with zero oscillation/overshoot.
   * $0 < \zeta < 1.0$ ($\text{bounce} \in (0, 1]$): **Underdamped** — overshoots target and oscillates with exponentially decaying amplitude.
   * $\zeta > 1.0$ ($\text{bounce} < 0$): **Overdamped** — approaches target with a flatter, higher-drag curve and zero overshoot.

The exact conversion from $(\tau, \zeta)$ to physical stiffness $k$, undamped natural angular frequency $\omega_0$, and damping coefficient $c$ is:

$$\omega_0 = \frac{2\pi}{\tau}, \quad k = m\omega_0^2 = m\left(\frac{2\pi}{\tau}\right)^2, \quad c = 2\zeta\sqrt{km} = \frac{4\pi \zeta m}{\tau} = \frac{4\pi(1 - b)m}{\tau}$$

### 4.2 Closed-Form Analytical Solutions for Any Delta Time $\Delta t$

Given initial displacement from target $x_0 = x_{\text{current}} - x_{\text{target}}$ and initial velocity $v_0$, the exact position $x(t)$ and velocity $v(t)$ after step $t = \Delta t$ are:

1. **Critically Damped ($\zeta = 1.0$)**:
   $$B = v_0 + \omega_0 x_0$$
   $$x(t) = \left(x_0 + B t\right)e^{-\omega_0 t}, \quad v(t) = \left(v_0 - \omega_0 B t\right)e^{-\omega_0 t}$$

2. **Underdamped ($\zeta < 1.0$)**:
   $$\omega_d = \omega_0\sqrt{1 - \zeta^2}, \quad B = \frac{v_0 + \zeta\omega_0 x_0}{\omega_d}$$
   $$x(t) = e^{-\zeta\omega_0 t}\left(x_0 \cos(\omega_d t) + B \sin(\omega_d t)\right)$$
   $$v(t) = e^{-\zeta\omega_0 t}\left(\left(B\omega_d - \zeta\omega_0 x_0\right)\cos(\omega_d t) - \left(x_0\omega_d + \zeta\omega_0 B\right)\sin(\omega_d t)\right)$$

3. **Overdamped ($\zeta > 1.0$)**:
   $$r_1 = -\omega_0\left(\zeta - \sqrt{\zeta^2 - 1}\right), \quad r_2 = -\omega_0\left(\zeta + \sqrt{\zeta^2 - 1}\right)$$
   $$c_2 = \frac{v_0 - r_1 x_0}{r_2 - r_1}, \quad c_1 = x_0 - c_2$$
   $$x(t) = c_1 e^{r_1 t} + c_2 e^{r_2 t}, \quad v(t) = c_1 r_1 e^{r_1 t} + c_2 r_2 e^{r_2 t}$$

### 4.3 Exponential Decay Momentum Projection & Asymptotic Rubber-Banding

When a user flicks a 3D turntable, carousel, or sheet with release velocity $v_0$ (in units/second), Apple rejects the textbook constant-friction kinematics formula $d = \frac{v^2}{2a}$. Instead, Apple models fluid drag using continuous **exponential velocity decay** governed by `UIScrollView.DecelerationRate` ($r = 0.998$ per ms for normal scrolling, $r = 0.990$ per ms for snappy pagination/turntables):

$$x_{\text{projected}} = x_0 + \left(\frac{v_0}{1000}\right) \cdot \frac{r}{1 - r}$$

When a user drags a scroll container, UI sheet, or 3D camera past its physical limit by raw distance $x$, Apple applies **asymptotic rubber-banding** with resistance coefficient $c = 0.55$ over container dimension $d$:

$$f(x, d, c) = \text{sgn}(x) \cdot \frac{|x| \cdot d \cdot c}{d + c \cdot |x|} = \text{sgn}(x) \cdot \left(1 - \frac{1}{\frac{|x| \cdot c}{d} + 1}\right) d$$

### 4.4 Production Physics Engine Module (Copy-Paste Ready Vanilla JS/TS)

Embed this exact module into every interactive project to handle 1D, 2D, and 3D springs, pointer velocity tracking, momentum projection, and rubber-banding:

```javascript
/**
 * ============================================================================
 * APPLE ANALYTICAL SPRING & MOMENTUM PHYSICS ENGINE (ZERO DEPENDENCIES)
 * Implements WWDC 2018 Session 803 & WWDC 2023 Session 10158 exact math.
 * ============================================================================
 */
export class AppleSpring {
  /**
   * @param {Object} config
   * @param {number} [config.response=0.45] - Duration of one undamped cycle in seconds
   * @param {number} [config.dampingFraction=1.0] - 1.0 = critically damped (0 bounce), 0.8 = momentum bounce
   * @param {number} [config.mass=1.0] - Normalized virtual mass
   * @param {number} [config.initialValue=0] - Starting value
   */
  constructor({
    response = 0.45,
    dampingFraction = 1.0,
    mass = 1.0,
    initialValue = 0
  } = {}) {
    this.mass = mass;
    this.currentValue = initialValue;
    this.targetValue = initialValue;
    this.velocity = 0;
    this.restDisplacementThreshold = 0.0004;
    this.restVelocityThreshold = 0.0004;
    this.configure({ response, dampingFraction, mass });
  }

  configure({ response = this.response, dampingFraction = this.dampingFraction, mass = this.mass }) {
    this.mass = Math.max(0.001, mass);
    this.response = Math.max(0.001, response);
    this.dampingFraction = Math.max(0.0, dampingFraction);
    this.omega0 = (2 * Math.PI) / this.response;
    this.stiffness = this.omega0 * this.omega0 * this.mass;
    this.damping = (4 * Math.PI * this.dampingFraction * this.mass) / this.response;
  }

  static smooth(duration = 0.4, initialValue = 0) {
    return new AppleSpring({ response: duration, dampingFraction: 1.0, initialValue });
  }

  static snappy(duration = 0.3, extraBounce = 0.0, initialValue = 0) {
    return new AppleSpring({ response: duration, dampingFraction: Math.max(0.1, 0.85 - extraBounce), initialValue });
  }

  static bouncy(duration = 0.5, extraBounce = 0.0, initialValue = 0) {
    return new AppleSpring({ response: duration, dampingFraction: Math.max(0.1, 0.70 - extraBounce), initialValue });
  }

  static interactive(initialValue = 0) {
    return new AppleSpring({ response: 0.15, dampingFraction: 0.86, initialValue });
  }

  /**
   * Immediately sets current and target value (used during 1:1 pointer tracking).
   */
  jumpTo(value, velocity = 0) {
    this.currentValue = value;
    this.targetValue = value;
    this.velocity = velocity;
  }

  /**
   * Retargets mid-flight while preserving presentation position and live velocity.
   */
  setTarget(newTarget, injectedVelocity = null) {
    if (injectedVelocity !== null && Number.isFinite(injectedVelocity)) {
      this.velocity = injectedVelocity;
    }
    this.targetValue = newTarget;
  }

  /**
   * Steps differential solver analytically by dt seconds (clamped for tab-switch safety).
   */
  step(dt) {
    const safeDt = Math.min(Math.max(dt, 0.0001), 0.1);
    const x0 = this.currentValue - this.targetValue;
    const v0 = this.velocity;

    if (
      Math.abs(x0) <= this.restDisplacementThreshold &&
      Math.abs(v0) <= this.restVelocityThreshold
    ) {
      this.currentValue = this.targetValue;
      this.velocity = 0;
      return { value: this.currentValue, velocity: 0, isAtRest: true };
    }

    const zeta = this.dampingFraction;
    const omega0 = this.omega0;
    let x1, v1;

    if (zeta < 1.0 - 1e-5) {
      // Underdamped
      const omegaD = omega0 * Math.sqrt(1.0 - zeta * zeta);
      const env = Math.exp(-zeta * omega0 * safeDt);
      const cosTerm = Math.cos(omegaD * safeDt);
      const sinTerm = Math.sin(omegaD * safeDt);
      const B = (v0 + zeta * omega0 * x0) / omegaD;

      x1 = env * (x0 * cosTerm + B * sinTerm);
      v1 = env * (
        (B * omegaD - zeta * omega0 * x0) * cosTerm -
        (x0 * omegaD + zeta * omega0 * B) * sinTerm
      );
    } else if (zeta > 1.0 + 1e-5) {
      // Overdamped
      const root = Math.sqrt(zeta * zeta - 1.0);
      const r1 = -omega0 * (zeta - root);
      const r2 = -omega0 * (zeta + root);
      const c2 = (v0 - r1 * x0) / (r2 - r1);
      const c1 = x0 - c2;
      const e1 = Math.exp(r1 * safeDt);
      const e2 = Math.exp(r2 * safeDt);

      x1 = c1 * e1 + c2 * e2;
      v1 = c1 * r1 * e1 + c2 * r2 * e2;
    } else {
      // Critically Damped (Default UI / Camera Rig)
      const env = Math.exp(-omega0 * safeDt);
      const B = v0 + omega0 * x0;

      x1 = env * (x0 + B * safeDt);
      v1 = env * (v0 - omega0 * B * safeDt);
    }

    this.currentValue = this.targetValue + x1;
    this.velocity = v1;

    const isAtRest =
      Math.abs(x1) <= this.restDisplacementThreshold &&
      Math.abs(v1) <= this.restVelocityThreshold;

    if (isAtRest) {
      this.currentValue = this.targetValue;
      this.velocity = 0;
    }

    return { value: this.currentValue, velocity: this.velocity, isAtRest };
  }
}

/**
 * Split 3D Vector Spring Controller
 * Rule: Always run independent 1D springs per axis so orthogonal velocities never drift out of phase.
 */
export class AppleVector3Spring {
  constructor({ response = 0.45, dampingFraction = 1.0, x = 0, y = 0, z = 0 } = {}) {
    this.x = new AppleSpring({ response, dampingFraction, initialValue: x });
    this.y = new AppleSpring({ response, dampingFraction, initialValue: y });
    this.z = new AppleSpring({ response, dampingFraction, initialValue: z });
  }

  jumpTo(x, y, z) {
    this.x.jumpTo(x);
    this.y.jumpTo(y);
    this.z.jumpTo(z);
  }

  setTarget(tx, ty, tz, vx = null, vy = null, vz = null) {
    this.x.setTarget(tx, vx);
    this.y.setTarget(ty, vy);
    this.z.setTarget(tz, vz);
  }

  step(dt) {
    const sx = this.x.step(dt);
    const sy = this.y.step(dt);
    const sz = this.z.step(dt);
    return {
      x: sx.value,
      y: sy.value,
      z: sz.value,
      isAtRest: sx.isAtRest && sy.isAtRest && sz.isAtRest
    };
  }
}

/**
 * Rolling 100ms Pointer Velocity Tracker for Seamless Gesture-to-Spring Handoff
 */
export class ApplePointerVelocityTracker {
  constructor(windowMs = 100) {
    this.windowMs = windowMs;
    this.samples = [];
  }

  reset() {
    this.samples.length = 0;
  }

  addSample(x, y, timestamp = performance.now()) {
    this.samples.push({ x, y, t: timestamp });
    const cutoff = timestamp - this.windowMs;
    while (this.samples.length > 2 && this.samples[0].t < cutoff) {
      this.samples.shift();
    }
  }

  getVelocity(now = performance.now()) {
    if (this.samples.length < 2) return { vx: 0, vy: 0 };
    const newest = this.samples[this.samples.length - 1];
    // If pointer stayed completely still for > 45ms before release, velocity is 0
    if (now - newest.t > 45) return { vx: 0, vy: 0 };

    const oldest = this.samples[0];
    const dtSeconds = (newest.t - oldest.t) / 1000;
    if (dtSeconds <= 0.001) return { vx: 0, vy: 0 };

    return {
      vx: (newest.x - oldest.x) / dtSeconds,
      vy: (newest.y - oldest.y) / dtSeconds
    };
  }
}

/**
 * Apple Momentum Projection, Snap Target Selection & Rubber-Banding Math
 */
export const ApplePhysicsMath = {
  DECELERATION_NORMAL: 0.998,
  DECELERATION_FAST: 0.990,

  projectRestingPoint(initialPosition, initialVelocity, decelerationRate = 0.998) {
    return initialPosition + (initialVelocity / 1000.0) * (decelerationRate / (1.0 - decelerationRate));
  },

  findNearestSnapTarget(initialPosition, initialVelocity, snapPoints, decelerationRate = 0.990) {
    const projected = this.projectRestingPoint(initialPosition, initialVelocity, decelerationRate);
    let closest = snapPoints[0];
    let minDiff = Math.abs(projected - closest);
    for (let i = 1; i < snapPoints.length; i++) {
      const diff = Math.abs(projected - snapPoints[i]);
      if (diff < minDiff) {
        minDiff = diff;
        closest = snapPoints[i];
      }
    }
    return closest;
  },

  rubberBand(offset, dimension, constant = 0.55) {
    if (dimension <= 0) return 0;
    const sign = offset < 0 ? -1 : 1;
    const absOffset = Math.abs(offset);
    return sign * ((1.0 - (1.0 / ((absOffset * constant / dimension) + 1.0))) * dimension);
  }
};
```

---

## 5. CONTINUOUS CURVATURE GEOMETRY & THE MATHEMATICS OF THE SQUIRCLE

A primary reason non-Apple UI and 3D hardware models look cheap is the use of **G1 tangent-continuous circular arcs** (`border-radius` in CSS or standard bevels in 3D).

### 5.1 G1 vs. G2/G3 Curvature Continuity

In a standard rounded rectangle, a straight line has curvature $\kappa = 0$. At the exact point where the straight edge meets the circular corner arc of radius $r$, curvature jumps instantaneously from $\kappa = 0$ to $\kappa = \frac{1}{r}$.
* **In 2D UI**: The human visual cortex perceives a subtle "kink" at the transition point.
* **In 3D PBR Rendering**: Under studio softbox reflections, a G1 discontinuity causes specular highlights to snap harshly along a visible seam line where the flat face meets the corner bevel.

Apple eliminates this seam by using a **Superellipse (Squircle)** with G2/G3 curvature continuity, governed by the Lamé curve equation:

$$\left|\frac{x}{a}\right|^n + \left|\frac{y}{b}\right|^n = 1 \quad \text{or} \quad \left|\frac{x}{a}\right|^{2K} + \left|\frac{y}{b}\right|^{2K} = 1$$

Where $n = 2$ ($K = 1$) is a circle, and $n = 4$ ($K = 2$, standardized in CSS Borders Level 4 as `corner-shape: squircle` or `superellipse(2)`) produces continuous curvature where $\kappa$ ramps smoothly from $0$ along the straight edge to its peak at the $45^\circ$ corner apex.

### 5.2 Cross-Browser SVG Squircle Generator & 3D Superellipse Geometry

Because native CSS `corner-shape: squircle` is not yet universally supported across all browser engines, pair `corner-shape: squircle` in CSS with this programmatic SVG/Canvas superellipse generator for hero hardware frames and Three.js extrusions:

```javascript
/**
 * Generates an exact G2-continuous Superellipse (Squircle) SVG Path or Three.js Shape.
 * @param {number} width - Width of container or 3D profile
 * @param {number} height - Height of container or 3D profile
 * @param {number} radius - Corner radius
 * @param {number} [smoothing=0.68] - Apple iOS/macOS corner smoothing factor (0.6 to 0.8)
 */
export function generateAppleSquirclePath(width, height, radius, smoothing = 0.68) {
  const r = Math.min(radius, width / 2, height / 2);
  // Extend bezier handle distance based on smoothing factor to ease into the straight edge
  const p = Math.min(width / 2, height / 2, r * (1 + smoothing));
  const c = r * 0.5522847498; // Standard circular cubic bezier constant adjusted for squircle
  const handleOffset = r - c * (1 - smoothing * 0.35);

  return [
    `M ${p} 0`,
    `L ${width - p} 0`,
    `C ${width - handleOffset} 0, ${width} ${handleOffset}, ${width} ${p}`,
    `L ${width} ${height - p}`,
    `C ${width} ${height - handleOffset}, ${width - handleOffset} ${height}, ${width - p} ${height}`,
    `L ${p} ${height}`,
    `C ${handleOffset} ${height}, 0 ${height - handleOffset}, 0 ${height - p}`,
    `L 0 ${p}`,
    `C 0 ${handleOffset}, ${handleOffset} 0, ${p} 0`,
    `Z`
  ].join(' ');
}

/**
 * Creates a Three.js ExtrudeGeometry with a true continuous Superellipse profile and smooth bevels
 * for authentic iPhone / MacBook / Apple Silicon 3D unibody enclosures.
 */
export function create3DSquircleChassisGeometry(THREE, {
  width = 2.4,
  height = 4.9,
  depth = 0.22,
  cornerRadius = 0.38,
  bevelThickness = 0.035,
  bevelSize = 0.03,
  curveSegments = 64
} = {}) {
  const shape = new THREE.Shape();
  const a = width / 2 - bevelSize;
  const b = height / 2 - bevelSize;
  const n = 4.2; // Apple hardware superellipse exponent

  // Sample parametric superellipse: x(theta) = a * sgn(cos) * |cos|^(2/n)
  // For corner localization, blend straight segments with superellipse corners
  const totalPoints = curveSegments * 4;
  const cr = Math.min(cornerRadius, a, b);

  for (let i = 0; i <= totalPoints; i++) {
    const theta = (i / totalPoints) * Math.PI * 2;
    const cosT = Math.cos(theta);
    const sinT = Math.sin(theta);

    // Superellipse corner offset + straight edge extension
    const sx = Math.sign(cosT) * Math.pow(Math.abs(cosT), 2 / n) * cr + Math.sign(cosT) * (a - cr) * Math.pow(Math.abs(cosT), 0.15);
    const sy = Math.sign(sinT) * Math.pow(Math.abs(sinT), 2 / n) * cr + Math.sign(sinT) * (b - cr) * Math.pow(Math.abs(sinT), 0.15);

    if (i === 0) shape.moveTo(sx, sy);
    else shape.lineTo(sx, sy);
  }
  shape.closePath();

  const extrudeSettings = {
    steps: 1,
    depth: depth - bevelThickness * 2,
    bevelEnabled: true,
    bevelThickness,
    bevelSize,
    bevelOffset: 0,
    bevelSegments: 16 // High segment count guarantees smooth specular roll across chamfers
  };

  const geo = new THREE.ExtrudeGeometry(shape, extrudeSettings);
  geo.center();
  geo.computeVertexNormals();
  return geo;
}
```

---

## 6. OPTICAL TYPOGRAPHY & CALIBRATED SEMANTIC COLOR SYSTEM

Apple's **SF Pro Variable** font interpolates smoothly across weight (`wght`), width (`wdth`), and optical size (`opsz`) between `17pt` and `28pt`. At small sizes (`< 20px`), strokes are sturdier and tracking is opened up (`+0.008em` to `+0.06em`) to prevent glyph crowding. At large display sizes (`> 28px`), tracking is tightened aggressively (`-0.016em` to `-0.04em`) and line-height is compressed (`1.04` to `1.12`) so headlines lock together as a precision graphic monolith.

### 6.1 Complete SF Pro Optical Sizing, Tracking & Leading Specification Table

| Role / Token Class | Size Range (px / rem) | Weight (`wght`) | Line Height | Optical Tracking (`letter-spacing`) | Max Measure / Constraint |
| :--- | :--- | :--- | :--- | :--- | :--- |
| `.type-hero-mega` | `56px – 96px` (`3.5rem – 6rem`) | `700` (Bold) | `1.04` | `-0.040em` | `14ch – 18ch` |
| `.type-headline-h1` | `40px – 64px` (`2.5rem – 4rem`) | `600` / `700` | `1.08` | `-0.028em` | `18ch – 24ch` |
| `.type-headline-h2` | `28px – 40px` (`1.75rem – 2.5rem`) | `600` (Semibold) | `1.12` | `-0.018em` | `24ch – 32ch` |
| `.type-headline-h3` | `21px – 26px` (`1.3125rem – 1.625rem`) | `600` (Semibold) | `1.20` | `-0.010em` | `32ch – 42ch` |
| `.type-callout-lead` | `19px – 22px` (`1.1875rem – 1.375rem`) | `500` (Medium) | `1.38` | `-0.004em` | `45ch – 58ch` |
| `.type-body-prose` | `17px` (`1.0625rem`) | `400` (Regular) | `1.47059` | `+0.008em` | `60ch – 65ch` |
| `.type-subhead-ui` | `15px` (`0.9375rem`) | `500` (Medium) | `1.35` | `+0.012em` | UI Labels / Cards |
| `.type-footnote-spec` | `13px` (`0.8125rem`) | `400` / `500` | `1.33` | `+0.022em` | Technical Footnotes |
| `.type-caption-micro` | `11px – 12px` (`0.6875rem – 0.75rem`) | `500` / `600` | `1.28` | `+0.045em` | Axis / Telemetry Units |

### 6.2 Master CSS Design System (Semantic Tokens, Liquid Glass & Accessibility)

```css
:root {
  /* Apple Font Stacks */
  --font-display: -apple-system, BlinkMacSystemFont, "SF Pro Display", "Inter", system-ui, sans-serif;
  --font-text: -apple-system, BlinkMacSystemFont, "SF Pro Text", "Inter", system-ui, sans-serif;
  --font-mono: "SF Mono", "JetBrains Mono", ui-monospace, SFMono-Regular, Menlo, monospace;

  /* First-Class Dark Mode Surface Hierarchy (Default for 3D Hardware Films) */
  --surface-canvas: #000000;
  --surface-reading-void: #09090B;
  --surface-elevated-1: #161618;
  --surface-elevated-2: #1C1C1E;
  --surface-elevated-3: #242426;
  --surface-well-inset: rgba(0, 0, 0, 0.48);

  /* visionOS 26 / iOS 26 Liquid Glass Tokens */
  --glass-bg-heavy: rgba(22, 22, 24, 0.78);
  --glass-bg-regular: rgba(28, 28, 30, 0.62);
  --glass-bg-pill: rgba(36, 36, 38, 0.72);
  --glass-border-hairline: rgba(255, 255, 255, 0.08);
  --glass-specular-rim: rgba(255, 255, 255, 0.20);

  /* Optical Text Luminance Hierarchy (Prevents OLED Halation) */
  --text-primary: #F5F5F7;
  --text-secondary: #86868B;
  --text-tertiary: #6E6E73;

  /* Calibrated Semantic System Accents (Dark Mode Luminance-Boosted) */
  --accent-blue: #0091FF; /* 6.49:1 contrast ratio on #000000 */
  --accent-blue-hover: #26A2FF;
  --accent-green: #30D158;
  --accent-amber: #FF9F0A;
  --accent-red: #FF453A;
  --focus-ring: rgba(0, 145, 255, 0.55);

  /* Authentic Apple Hardware Anodization Finishes */
  --finish-natural-titanium: #C8C2B8;
  --finish-desert-titanium: #BFA48F;
  --finish-white-titanium: #F2F1ED;
  --finish-black-titanium: #2C2C2E;
  --finish-space-black: #1D1D1F;
  --finish-silver-aluminum: #E3E4E6;

  /* Front-Loaded High-Tension Easing Curves */
  --ease-apple-out: cubic-bezier(0.22, 1, 0.36, 1);
  --ease-apple-in-out: cubic-bezier(0.65, 0, 0.35, 1);
}

[data-theme="light"] {
  --surface-canvas: #FFFFFF;
  --surface-reading-void: #F5F5F7;
  --surface-elevated-1: #F5F5F7;
  --surface-elevated-2: #E8E8ED;
  --surface-elevated-3: #D2D2D7;
  --surface-well-inset: rgba(0, 0, 0, 0.05);
  --glass-bg-heavy: rgba(245, 245, 247, 0.84);
  --glass-bg-regular: rgba(255, 255, 255, 0.68);
  --glass-bg-pill: rgba(240, 240, 245, 0.78);
  --glass-border-hairline: rgba(0, 0, 0, 0.08);
  --glass-specular-rim: rgba(255, 255, 255, 0.90);
  --text-primary: #1D1D1F;
  --text-secondary: #6E6E73;
  --text-tertiary: #86868B;
  --accent-blue: #0088FF;
}

*, *::before, *::after {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

html, body {
  background-color: var(--surface-canvas);
  color: var(--text-primary);
  font-family: var(--font-text);
  font-size: 100%;
  line-height: 1.47059;
  letter-spacing: 0.008em;
  overflow-x: hidden;
  selection-background-color: var(--accent-blue);
}

/* Optical Typography Utility Classes */
.type-hero-mega {
  font-family: var(--font-display);
  font-size: clamp(3.25rem, 6.8vw, 6rem);
  font-weight: 700;
  line-height: 1.04;
  letter-spacing: -0.04em;
}

.type-headline-h1 {
  font-family: var(--font-display);
  font-size: clamp(2.35rem, 4.5vw, 4rem);
  font-weight: 600;
  line-height: 1.08;
  letter-spacing: -0.028em;
}

.type-headline-h2 {
  font-family: var(--font-display);
  font-size: clamp(1.75rem, 3vw, 2.5rem);
  font-weight: 600;
  line-height: 1.12;
  letter-spacing: -0.018em;
}

.type-callout-lead {
  font-family: var(--font-display);
  font-size: clamp(1.1875rem, 1.6vw, 1.375rem);
  font-weight: 500;
  line-height: 1.38;
  letter-spacing: -0.004em;
  color: var(--text-secondary);
}

.type-body-prose {
  font-family: var(--font-text);
  font-size: 1.0625rem;
  font-weight: 400;
  line-height: 1.47059;
  letter-spacing: 0.008em;
  max-width: 65ch;
}

/* Contrast-by-Luminance Headline Pattern (Replaces AI Slop Gradient Words) */
.text-dimmed { color: var(--text-secondary); }
.text-bright { color: var(--text-primary); }

/* Mandatory Tabular Numbers for All Metrics & Animated Counters */
.tabular-nums {
  font-variant-numeric: tabular-nums;
  font-feature-settings: "tnum" 1;
}

/* Asymmetric 12-Column Bento Grid Architecture */
.apple-bento-grid {
  display: grid;
  grid-template-columns: repeat(12, minmax(0, 1fr));
  gap: 20px;
  width: 100%;
  max-width: 1240px;
  margin: 0 auto;
  padding: 0 24px;
}

.apple-bento-card {
  position: relative;
  background: var(--surface-elevated-1);
  border-radius: 28px;
  corner-shape: squircle;
  border: 1px solid var(--glass-border-hairline);
  box-shadow: inset 0 1px 0 0 var(--glass-specular-rim);
  padding: 36px;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

/* Liquid Glass Spatial Callout & Control Bar */
.apple-liquid-glass {
  background: var(--glass-bg-regular);
  backdrop-filter: saturate(180%) blur(24px);
  -webkit-backdrop-filter: saturate(180%) blur(24px);
  border-radius: 20px;
  corner-shape: squircle;
  border: 1px solid var(--glass-border-hairline);
  box-shadow:
    inset 0 1px 0 0 var(--glass-specular-rim),
    0 24px 48px -12px rgba(0, 0, 0, 0.65);
  transition: opacity 220ms var(--ease-apple-out), transform 220ms var(--ease-apple-out);
}

/* Anti-Vection Comfort Fade Class (Applied during rapid 3D camera movement) */
.apple-liquid-glass.is-camera-moving {
  opacity: 0.18;
}

/* 8-State Interactive Pill Button (Responds on Pointer-Down in 60ms) */
.apple-btn-primary {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
  padding: 12px 26px;
  font-family: var(--font-text);
  font-size: 1.0625rem;
  font-weight: 500;
  letter-spacing: -0.01em;
  color: #FFFFFF;
  background-color: var(--accent-blue);
  border: 1px solid rgba(255, 255, 255, 0.14);
  border-radius: 9999px;
  cursor: pointer;
  user-select: none;
  -webkit-tap-highlight-color: transparent;
  transition:
    transform 80ms var(--ease-apple-out),
    background-color 140ms var(--ease-apple-out),
    box-shadow 140ms var(--ease-apple-out);
}

.apple-btn-primary:hover { background-color: var(--accent-blue-hover); }
.apple-btn-primary:active { transform: scale(0.97); transition-duration: 60ms; }
.apple-btn-primary:focus-visible { outline: none; box-shadow: 0 0 0 4px var(--focus-ring); }
.apple-btn-primary:disabled, .apple-btn-primary[aria-disabled="true"] { opacity: 0.4; pointer-events: none; }

/* Mandatory Accessibility Media Queries */
@media (prefers-reduced-motion: reduce) {
  *, *::before, *::after {
    animation-duration: 0.01ms !important;
    animation-iteration-count: 1 !important;
    transition-duration: 120ms !important;
    scroll-behavior: auto !important;
  }
}

@media (prefers-reduced-transparency: reduce) {
  .apple-liquid-glass {
    background: var(--surface-elevated-2);
    backdrop-filter: none;
    -webkit-backdrop-filter: none;
  }
}

@media (prefers-contrast: more) {
  :root {
    --glass-border-hairline: rgba(255, 255, 255, 0.4);
    --text-secondary: #D2D2D7;
  }
}
```

---

## 7. 3D WEBGL / THREE.JS STUDIO PIPELINE, PBR MATERIALS & GLSL SHADERS

When building a 3D product advertisement in pure code, the Agent must never rely on external `.hdr` files that may fail to load or CORS-block in sandboxes. Instead, generate a **Procedural Studio Softbox Environment Map (`PMREMGenerator`)**, **Procedural Anisotropic Micro-Groove Normal/Roughness Maps**, **Physically Based Materials (`MeshPhysicalMaterial`)**, and **Custom GLSL Specular Sweep Shaders**.

### 7.1 Procedural Studio Stage, Softbox Lightformers & Two-Node Camera Rig

```javascript
import * as THREE from 'three';

/**
 * Creates a complete Apple Industrial Design Studio Stage in Three.js.
 * Features ACESFilmicToneMapping, Procedural Softbox Lightformers, 3-Point Kinetic Lights,
 * and a Two-Node Damped Spring Camera Rig.
 */
export function createAppleStudioStage(canvas, { AppleVector3Spring }) {
  const scene = new THREE.Scene();
  scene.background = new THREE.Color(0x000000);

  // Cinematic 50mm-equivalent vertical FOV (30 deg) prevents wide-angle perspective distortion on hardware
  const camera = new THREE.PerspectiveCamera(30, window.innerWidth / window.innerHeight, 0.1, 100);

  // Two-Node Spring Camera Rig: Position Node + LookAt Target Node
  const cameraPositionSpring = new AppleVector3Spring({ response: 0.65, dampingFraction: 1.0, x: 0, y: 0.2, z: 7.2 });
  const cameraLookAtSpring = new AppleVector3Spring({ response: 0.55, dampingFraction: 1.0, x: 0, y: 0, z: 0 });
  const currentLookAtVec = new THREE.Vector3(0, 0, 0);

  const renderer = new THREE.WebGLRenderer({
    canvas,
    antialias: true,
    powerPreference: 'high-performance',
    alpha: false
  });
  renderer.setPixelRatio(Math.min(window.devicePixelRatio, 2.0));
  renderer.setSize(window.innerWidth, window.innerHeight);

  // Mandatory Filmic Tone Mapping for natural metallic highlight compression
  renderer.toneMapping = THREE.ACESFilmicToneMapping;
  renderer.toneMappingExposure = 1.15;
  renderer.outputColorSpace = THREE.SRGBColorSpace;
  renderer.shadowMap.enabled = true;
  renderer.shadowMap.type = THREE.PCFSoftShadowMap;

  // Build Procedural Dark Studio Room with High-Dynamic-Range Softbox Strips
  const pmremGenerator = new THREE.PMREMGenerator(renderer);
  pmremGenerator.compileEquirectangularShader();
  const envScene = new THREE.Scene();
  envScene.background = new THREE.Color(0x000000);

  // 1. Primary Overhead Softbox Strip (Creates crisp white reflection line across top chamfers)
  const topStrip = new THREE.Mesh(
    new THREE.PlaneGeometry(14, 2.2),
    new THREE.MeshBasicMaterial({ color: new THREE.Color(9.0, 9.2, 9.8), side: THREE.DoubleSide })
  );
  topStrip.position.set(0, 6.5, 2.0);
  topStrip.rotation.x = Math.PI / 2.15;
  envScene.add(topStrip);

  // 2. Left Warm-Neutral Key Softbox
  const leftStrip = new THREE.Mesh(
    new THREE.PlaneGeometry(1.8, 11),
    new THREE.MeshBasicMaterial({ color: new THREE.Color(6.0, 5.8, 5.5), side: THREE.DoubleSide })
  );
  leftStrip.position.set(-7.5, 0.5, 1.0);
  leftStrip.rotation.y = Math.PI / 2.2;
  envScene.add(leftStrip);

  // 3. Right Cool Rim Softbox
  const rightStrip = new THREE.Mesh(
    new THREE.PlaneGeometry(1.4, 11),
    new THREE.MeshBasicMaterial({ color: new THREE.Color(4.5, 5.5, 7.5), side: THREE.DoubleSide })
  );
  rightStrip.position.set(7.5, -0.5, -1.5);
  rightStrip.rotation.y = -Math.PI / 2.2;
  envScene.add(rightStrip);

  // 4. Subtle Floor Bounce Strip
  const floorBounce = new THREE.Mesh(
    new THREE.PlaneGeometry(10, 6),
    new THREE.MeshBasicMaterial({ color: new THREE.Color(0.8, 0.85, 0.95), side: THREE.DoubleSide })
  );
  floorBounce.position.set(0, -6.5, 0);
  floorBounce.rotation.x = -Math.PI / 2;
  envScene.add(floorBounce);

  scene.environment = pmremGenerator.fromScene(envScene, 0.035).texture;
  pmremGenerator.dispose();

  // Dynamic 3-Point Studio Light Rig (Choreographed during scroll / timeline acts)
  const keyLight = new THREE.SpotLight(0xffffff, 52);
  keyLight.position.set(4.5, 6.0, 5.5);
  keyLight.angle = Math.PI / 3.8;
  keyLight.penumbra = 0.88;
  keyLight.castShadow = true;
  keyLight.shadow.mapSize.set(2048, 2048);
  keyLight.shadow.bias = -0.0001;
  scene.add(keyLight);

  const rimLight = new THREE.PointLight(0xdbeafe, 32, 22);
  rimLight.position.set(-5.0, -3.2, -4.0);
  scene.add(rimLight);

  const fillLight = new THREE.HemisphereLight(0xffffff, 0x141416, 0.35);
  scene.add(fillLight);

  function stepCameraRig(dt) {
    const pos = cameraPositionSpring.step(dt);
    const look = cameraLookAtSpring.step(dt);
    camera.position.set(pos.x, pos.y, pos.z);
    currentLookAtVec.set(look.x, look.y, look.z);
    camera.lookAt(currentLookAtVec);
    return {
      isCameraMoving: !(pos.isAtRest && look.isAtRest)
    };
  }

  return {
    scene,
    camera,
    renderer,
    lights: { keyLight, rimLight, fillLight },
    cameraRig: {
      positionSpring: cameraPositionSpring,
      lookAtSpring: cameraLookAtSpring,
      step: stepCameraRig
    }
  };
}
```

### 7.2 Procedural Micro-Machined Texture & PBR Material Library

```javascript
/**
 * Generates a procedural brushed / bead-blasted micro-roughness texture in memory
 * so metallic shaders exhibit authentic anisotropic surface grain without external assets.
 */
export function createProceduralBrushedMetalTexture(THREE, size = 512) {
  const canvas = document.createElement('canvas');
  canvas.width = size;
  canvas.height = size;
  const ctx = canvas.getContext('2d');

  ctx.fillStyle = '#808080';
  ctx.fillRect(0, 0, size, size);

  for (let y = 0; y < size; y++) {
    const streakIntensity = 115 + Math.floor(Math.random() * 26);
    ctx.fillStyle = `rgb(${streakIntensity}, ${streakIntensity}, ${streakIntensity})`;
    ctx.fillRect(0, y, size, 1);
  }

  const texture = new THREE.CanvasTexture(canvas);
  texture.wrapS = THREE.RepeatWrapping;
  texture.wrapT = THREE.RepeatWrapping;
  texture.repeat.set(4, 4);
  return texture;
}

/**
 * Factory for Calibrated Apple Hardware Materials
 */
export const AppleMaterials = {
  titanium(THREE, hexColor = 0xc8c2b8, grainTexture = null) {
    const mat = new THREE.MeshPhysicalMaterial({
      color: new THREE.Color(hexColor),
      metalness: 0.92,
      roughness: 0.26,
      roughnessMap: grainTexture || null,
      envMapIntensity: 1.4,
      clearcoat: 0.14,
      clearcoatRoughness: 0.32
    });
    if ('anisotropy' in mat) {
      mat.anisotropy = 0.65;
      mat.anisotropyRotation = Math.PI / 2;
    }
    return mat;
  },

  anodizedAluminum(THREE, hexColor = 0x1d1d1f) {
    return new THREE.MeshPhysicalMaterial({
      color: new THREE.Color(hexColor),
      metalness: 0.88,
      roughness: 0.22,
      envMapIntensity: 1.3,
      clearcoat: 0.08,
      clearcoatRoughness: 0.25
    });
  },

  polishedStainlessMirror(THREE, hexColor = 0xf5f5f7) {
    return new THREE.MeshPhysicalMaterial({
      color: new THREE.Color(hexColor),
      metalness: 1.0,
      roughness: 0.03,
      envMapIntensity: 1.85,
      clearcoat: 0.6,
      clearcoatRoughness: 0.02
    });
  },

  ceramicShieldGlass(THREE) {
    const probe = new THREE.MeshPhysicalMaterial();
    if ('transmission' in probe) {
      return new THREE.MeshPhysicalMaterial({
        color: new THREE.Color(0xffffff),
        metalness: 0.0,
        roughness: 0.025,
        transmission: 0.95,
        ior: 1.52,
        thickness: 0.35,
        attenuationColor: new THREE.Color(0xeef6ff),
        attenuationDistance: 2.0,
        clearcoat: 1.0,
        clearcoatRoughness: 0.02,
        envMapIntensity: 1.65
      });
    }
    return new THREE.MeshStandardMaterial({
      color: 0xffffff,
      metalness: 0.1,
      roughness: 0.05,
      transparent: true,
      opacity: 0.32
    });
  },

  oledDisplayWithClearcoat(THREE, displayTexture) {
    return new THREE.MeshPhysicalMaterial({
      map: displayTexture,
      emissiveMap: displayTexture,
      emissive: new THREE.Color(0xffffff),
      emissiveIntensity: 0.88,
      roughness: 0.05,
      metalness: 0.04,
      clearcoat: 1.0,
      clearcoatRoughness: 0.03,
      reflectivity: 0.95,
      envMapIntensity: 1.25
    });
  }
};
```

### 7.3 Custom GLSL Shader: Specular Awakening Sweep & Fresnel Silhouette Rim

Used in Act I of Apple commercials and Silicon chip reveals: a razor-sharp diagonal light band (`uSweepProgress`) glides across a dark monolithic surface while a Schlick Fresnel rim carves the silhouette out of the `#000000` background.

```javascript
export function createAppleSpecularSweepShader(THREE, {
  baseColor = '#121214',
  rimColor = '#0091FF',
  sweepColor = '#FFFFFF'
} = {}) {
  return new THREE.ShaderMaterial({
    uniforms: {
      uSweepProgress: { value: -1.4 }, // Scrubbed from -1.5 to +1.5
      uBaseColor: { value: new THREE.Color(baseColor) },
      uRimColor: { value: new THREE.Color(rimColor) },
      uSweepColor: { value: new THREE.Color(sweepColor) },
      uLightDir: { value: new THREE.Vector3(0.45, 0.75, 0.65).normalize() }
    },
    vertexShader: /* glsl */ `
      varying vec3 vNormal;
      varying vec3 vViewPosition;
      varying vec3 vWorldPosition;
      varying vec2 vUv;

      void main() {
        vUv = uv;
        vec4 worldPos = modelMatrix * vec4(position, 1.0);
        vWorldPosition = worldPos.xyz;
        vec4 mvPos = viewMatrix * worldPos;
        vViewPosition = -mvPos.xyz;
        vNormal = normalize(normalMatrix * normal);
        gl_Position = projectionMatrix * mvPos;
      }
    `,
    fragmentShader: /* glsl */ `
      uniform float uSweepProgress;
      uniform vec3 uBaseColor;
      uniform vec3 uRimColor;
      uniform vec3 uSweepColor;
      uniform vec3 uLightDir;

      varying vec3 vNormal;
      varying vec3 vViewPosition;
      varying vec3 vWorldPosition;
      varying vec2 vUv;

      void main() {
        vec3 normal = normalize(vNormal);
        vec3 viewDir = normalize(vViewPosition);

        // 1. Schlick Fresnel Silhouette Rim
        float NdotV = max(dot(normal, viewDir), 0.0);
        float fresnel = pow(1.0 - NdotV, 3.8);

        // 2. High-Tension Specular Lobe
        vec3 halfVec = normalize(uLightDir + viewDir);
        float NdotH = max(dot(normal, halfVec), 0.0);
        float specLobe = pow(NdotH, 110.0) * 0.85;

        // 3. Controlled Diagonal Specular Light Sweep
        float diag = (vWorldPosition.x * 0.68 + vWorldPosition.y * 0.68);
        float dist = abs(diag - uSweepProgress);
        float outerGlow = smoothstep(0.42, 0.0, dist);
        float razorCore = smoothstep(0.06, 0.0, dist);

        vec3 col = uBaseColor;
        col += uSweepColor * specLobe;
        col += uSweepColor * (outerGlow * 0.30 + razorCore * 0.95) * (0.25 + 0.75 * NdotH);
        col += uRimColor * fresnel * 0.70;

        gl_FragColor = vec4(col, 1.0);
      }
    `
  });
}
```

---

## 8. SCROLL-DRIVEN STORYTELLING, EXPLODED-VIEW CHOREOGRAPHY & 3D-TO-DOM PINNING

### 8.1 Cascading Exploded-View Math & 3D World-to-2D Screen Projection

During an exploded-view architectural breakdown, floating `.apple-liquid-glass` callouts must track specific 3D hardware layers in real time. Use `projectWorldToScreen` to map 3D anchor coordinates into 2D CSS transforms without triggering DOM layout thrashing:

```javascript
/**
 * Updates a staggered, cascading exploded-view animation across N hardware layers
 * from a single normalized scroll progress globalT in [0, 1].
 */
export function updateCascadingExplodedView(THREE, layers, globalT) {
  const count = layers.length;
  const staggerWindow = 0.82; // Spread layer triggers across first 82% of scroll range
  const layerSpan = 1.0 - staggerWindow;

  // Controlled easeOutBack with physical settle (s = 1.12)
  const easeOutBack = (t) => {
    const c1 = 1.12;
    const c3 = c1 + 1.0;
    return 1.0 + c3 * Math.pow(t - 1.0, 3) + c1 * Math.pow(t - 1.0, 2);
  };

  for (let i = 0; i < count; i++) {
    const item = layers[i];
    const revealAt = count > 1 ? (i / (count - 1)) * staggerWindow : 0;
    const localRaw = (globalT - revealAt) / layerSpan;
    const localClamped = Math.max(0.0, Math.min(1.0, localRaw));
    const eased = easeOutBack(localClamped);

    item.mesh.position.set(
      THREE.MathUtils.lerp(item.closedPos.x, item.explodedPos.x, eased),
      THREE.MathUtils.lerp(item.closedPos.y, item.explodedPos.y, eased),
      THREE.MathUtils.lerp(item.closedPos.z, item.explodedPos.z, eased)
    );
    item.mesh.rotation.set(
      THREE.MathUtils.lerp(item.closedRot.x, item.explodedRot.x, eased),
      THREE.MathUtils.lerp(item.closedRot.y, item.explodedRot.y, eased),
      THREE.MathUtils.lerp(item.closedRot.z, item.explodedRot.z, eased)
    );
  }
}

/**
 * Projects a 3D world-space anchor on a hardware mesh to 2D viewport pixel coordinates
 * for GPU-accelerated translate3d() positioning of floating Liquid Glass callouts.
 */
export function project3DAnchorToScreen(THREE, worldPosition, camera, viewportWidth, viewportHeight) {
  const projected = worldPosition.clone().project(camera);
  const screenX = (projected.x * 0.5 + 0.5) * viewportWidth;
  const screenY = (-(projected.y * 0.5) + 0.5) * viewportHeight;
  const isBehindCamera = projected.z > 1.0;
  return { x: screenX, y: screenY, visible: !isBehindCamera };
}
```

### 8.2 The Five-Act Apple Commercial & Interactive Launch Storyboard Blueprint

Every 3D advertisement or interactive product showcase generated under this skill must follow this five-act narrative structure:

| Act | Timeline / Scroll Range | Narrative Objective | 3D Camera & Lighting Choreography | Typography & UI Execution |
| :--- | :--- | :--- | :--- | :--- |
| **Act I** | `0% – 18%` | **The Monolith & Specular Awakening** | Pitch-black `#000000` void. Diagonal specular light sweep (`uSweepProgress: -1.4 -> +1.4`) traces the superellipse edge before the overhead softbox and Key Light ramp up. | Single `.type-hero-mega` headline rises from a clipped mask with contrast-by-luminance subhead. |
| **Act II** | `18% – 40%` | **Continuous Curvature & Material Macro** | Two-Node Camera orbits $90^\circ$ and pushes in (`30°` FOV) to a macro close-up of the bead-blasted titanium chamfer. Key Light orbits in an opposing arc so highlights roll across bevels. | Left-aligned `.type-headline-h2` + `.type-callout-lead` copy block occupying 4 columns; 8 columns reserved for unobstructed 3D hardware. |
| **Act III** | `40% – 68%` | **The Architectural Exploded View** | Viewport pins. Hardware separates along its optical Z/Y axis via `updateCascadingExplodedView` into Ceramic Shield, OLED Display, M-Series Silicon Die, Thermal Vapor Chamber, and Unibody Enclosure. | Floating `.apple-liquid-glass` callouts anchored to 3D layer coordinates via `project3DAnchorToScreen`, displaying `.tabular-nums` engineering specs. |
| **Act IV** | `68% – 88%` | **1:1 Interactive Turntable & Finish Studio** | User grabs and rotates the 3D hardware directly (`setPointerCapture`, 1:1 pointer tracking, handing release velocity from `ApplePointerVelocityTracker` into `AppleVector3Spring`) and switches anodization finishes. | Single floating `.apple-liquid-glass` pill bar with physical color swatches (`:active` `scale(0.97)` in `60ms`) + Asymmetric 12-Column Bento Grid below. |
| **Act V** | `88% – 100%` | **Convergence Lockup & Single Primary CTA** | Exploded layers converge back into a unified monolith under balanced 3-point studio lighting. | Clean product lockup, transparent pricing in `.tabular-nums`, and **exactly one** `.apple-btn-primary` CTA. |

---

## 9. REFERENCE MASTER TEMPLATE (COMPLETE 3D HARDWARE COMMERCIAL ARCHITECTURE)

Below is the canonical architectural wiring demonstrating how the analytical spring engine, procedural Three.js studio stage, superellipse hardware geometry, exploded-view choreographer, and 1:1 pointer velocity handoff integrate into a single high-craft execution loop:

```javascript
/**
 * CANONICAL EXECUTION LOOP WIRING FOR APPLE 3D WEBGL ADVERTISEMENTS
 * Combines Scroll-Scrubbed 5-Act Choreography + 1:1 Interruptible Pointer Physics
 */
export function mountApple3DProductShowcase({
  THREE,
  canvas,
  scrollContainer,
  calloutElements,
  finishButtons,
  AppleSpring,
  AppleVector3Spring,
  ApplePointerVelocityTracker,
  createAppleStudioStage,
  create3DSquircleChassisGeometry,
  createProceduralBrushedMetalTexture,
  AppleMaterials,
  updateCascadingExplodedView,
  project3DAnchorToScreen
}) {
  // 1. Initialize Studio Stage & Procedural Softbox Environment
  const { scene, camera, renderer, lights, cameraRig } = createAppleStudioStage(canvas, { AppleVector3Spring });
  const grainTex = createProceduralBrushedMetalTexture(THREE, 512);

  // 2. Construct Multi-Layer Superellipse Hardware Assembly (Assembled + Exploded States)
  const hardwareGroup = new THREE.Group();
  scene.add(hardwareGroup);

  const chassisMat = AppleMaterials.titanium(THREE, 0xc8c2b8, grainTex);
  const chassisColorR = new AppleSpring({ response: 0.35, dampingFraction: 1.0, initialValue: chassisMat.color.r });
  const chassisColorG = new AppleSpring({ response: 0.35, dampingFraction: 1.0, initialValue: chassisMat.color.g });
  const chassisColorB = new AppleSpring({ response: 0.35, dampingFraction: 1.0, initialValue: chassisMat.color.b });

  const unibodyMesh = new THREE.Mesh(
    create3DSquircleChassisGeometry(THREE, { width: 2.4, height: 4.9, depth: 0.22, cornerRadius: 0.38 }),
    chassisMat
  );
  const siliconCoreMesh = new THREE.Mesh(
    create3DSquircleChassisGeometry(THREE, { width: 1.1, height: 1.1, depth: 0.05, cornerRadius: 0.12 }),
    AppleMaterials.anodizedAluminum(THREE, 0x141416)
  );
  const ceramicGlassMesh = new THREE.Mesh(
    create3DSquircleChassisGeometry(THREE, { width: 2.28, height: 4.78, depth: 0.03, cornerRadius: 0.34 }),
    AppleMaterials.ceramicShieldGlass(THREE)
  );

  hardwareGroup.add(unibodyMesh, siliconCoreMesh, ceramicGlassMesh);

  const explodedLayers = [
    {
      mesh: unibodyMesh,
      closedPos: new THREE.Vector3(0, 0, -0.05),
      explodedPos: new THREE.Vector3(0, -0.35, -0.95),
      closedRot: new THREE.Vector3(0, 0, 0),
      explodedRot: new THREE.Vector3(0.08, -0.15, 0)
    },
    {
      mesh: siliconCoreMesh,
      closedPos: new THREE.Vector3(0, 0, 0.02),
      explodedPos: new THREE.Vector3(0, 0.0, 0.15),
      closedRot: new THREE.Vector3(0, 0, 0),
      explodedRot: new THREE.Vector3(0.04, 0.12, 0.05)
    },
    {
      mesh: ceramicGlassMesh,
      closedPos: new THREE.Vector3(0, 0, 0.09),
      explodedPos: new THREE.Vector3(0, 0.35, 1.10),
      closedRot: new THREE.Vector3(0, 0, 0),
      explodedRot: new THREE.Vector3(-0.06, 0.18, 0)
    }
  ];

  // 3. Interactive 1:1 Pointer Drag + Momentum Handoff Springs (LAW-01, LAW-02, LAW-03)
  const turntableRotX = new AppleSpring({ response: 0.45, dampingFraction: 0.82, initialValue: 0.12 });
  const turntableRotY = new AppleSpring({ response: 0.45, dampingFraction: 0.82, initialValue: -0.35 });
  const velocityTracker = new ApplePointerVelocityTracker(100);
  let isDragging = false;
  let lastPointerX = 0;
  let lastPointerY = 0;

  canvas.addEventListener('pointerdown', (e) => {
    isDragging = true;
    lastPointerX = e.clientX;
    lastPointerY = e.clientY;
    velocityTracker.reset();
    velocityTracker.addSample(e.clientX, e.clientY, performance.now());
    canvas.setPointerCapture(e.pointerId);
  });

  canvas.addEventListener('pointermove', (e) => {
    if (!isDragging) return;
    const dx = e.clientX - lastPointerX;
    const dy = e.clientY - lastPointerY;
    lastPointerX = e.clientX;
    lastPointerY = e.clientY;
    velocityTracker.addSample(e.clientX, e.clientY, performance.now());

    // 1:1 Immediate Tracking during active gesture
    const nextY = turntableRotY.currentValue + dx * 0.007;
    const nextX = Math.max(-0.85, Math.min(0.85, turntableRotX.currentValue + dy * 0.007));
    turntableRotY.jumpTo(nextY);
    turntableRotX.jumpTo(nextX);
  });

  const handlePointerRelease = (e) => {
    if (!isDragging) return;
    isDragging = false;
    if (canvas.hasPointerCapture(e.pointerId)) {
      canvas.releasePointerCapture(e.pointerId);
    }
    // Extract exact release velocity and inject into momentum spring solver
    const { vx, vy } = velocityTracker.getVelocity(performance.now());
    const angularVelY = vx * 0.007;
    const angularVelX = vy * 0.007;
    const projectedY = turntableRotY.currentValue + angularVelY * 0.18;
    const projectedX = Math.max(-0.65, Math.min(0.65, turntableRotX.currentValue + angularVelX * 0.18));

    turntableRotY.configure({ response: 0.5, dampingFraction: 0.80 });
    turntableRotX.configure({ response: 0.5, dampingFraction: 0.80 });
    turntableRotY.setTarget(projectedY, angularVelY);
    turntableRotX.setTarget(projectedX, angularVelX);
  };

  canvas.addEventListener('pointerup', handlePointerRelease);
  canvas.addEventListener('pointercancel', handlePointerRelease);

  // 4. Hardware Anodization Finish Switcher (Critically Damped Chromatic Transition)
  finishButtons.forEach((btn) => {
    btn.addEventListener('click', () => {
      const targetColor = new THREE.Color(btn.dataset.hex || '#c8c2b8');
      chassisColorR.setTarget(targetColor.r);
      chassisColorG.setTarget(targetColor.g);
      chassisColorB.setTarget(targetColor.b);
    });
  });

  // 5. Scroll Progress Listener & Reduced-Motion Detection
  const reducedMotionQuery = window.matchMedia('(prefers-reduced-motion: reduce)');
  const scrollSpring = new AppleSpring({ response: 0.42, dampingFraction: 1.0, initialValue: 0 });

  window.addEventListener('scroll', () => {
    const maxScroll = Math.max(1, document.documentElement.scrollHeight - window.innerHeight);
    const normalized = Math.max(0, Math.min(1, window.scrollY / maxScroll));
    if (reducedMotionQuery.matches) {
      scrollSpring.jumpTo(normalized);
    } else {
      scrollSpring.setTarget(normalized);
    }
  }, { passive: true });

  // 6. High-Precision ProMotion (60fps / 120Hz) Render Loop
  let lastFrameTime = performance.now();

  function tick(now) {
    requestAnimationFrame(tick);
    const dt = (now - lastFrameTime) / 1000;
    lastFrameTime = now;

    // Step Scroll & Material Color Springs
    const scrollState = scrollSpring.step(dt);
    const t = scrollState.value;

    chassisMat.color.setRGB(
      chassisColorR.step(dt).value,
      chassisColorG.step(dt).value,
      chassisColorB.step(dt).value
    );

    // Choreograph Kinetic Studio Key Light Arc (LAW-04)
    const lightAngle = t * Math.PI * 1.25 - 0.4;
    lights.keyLight.position.set(
      Math.cos(lightAngle) * 5.5,
      4.5 + Math.sin(t * Math.PI) * 2.0,
      Math.sin(lightAngle) * 5.5
    );

    // Evaluate Exploded View Window (Act III: t in [0.35, 0.72])
    const explodeProgress = t < 0.35 ? 0 : t <= 0.60 ? (t - 0.35) / 0.25 : t <= 0.80 ? 1.0 - (t - 0.60) / 0.20 : 0;
    updateCascadingExplodedView(THREE, explodedLayers, explodeProgress);

    // Step Interactive Turntable Springs + Subtle Scroll Presentation Orbit
    const rotX = turntableRotX.step(dt).value;
    const rotY = turntableRotY.step(dt).value;
    hardwareGroup.rotation.set(rotX, rotY + t * Math.PI * 0.65, 0);

    // Step Two-Node Camera Rig & Apply Anti-Vection Comfort Fade to Floating Glass Callouts
    const { isCameraMoving } = cameraRig.step(dt);
    calloutElements.forEach((item) => {
      item.el.classList.toggle('is-camera-moving', isCameraMoving);
      const worldPos = new THREE.Vector3();
      item.layerMesh.getWorldPosition(worldPos);
      const screen = project3DAnchorToScreen(THREE, worldPos, camera, window.innerWidth, window.innerHeight);
      item.el.style.transform = `translate3d(${screen.x.toFixed(1)}px, ${screen.y.toFixed(1)}px, 0)`;
    });

    renderer.render(scene, camera);
  }

  requestAnimationFrame(tick);
}
```

---

## 10. MANDATORY 12-GATE PRE-FLIGHT AUDIT PROTOCOL

Before outputting any final HTML, CSS, JavaScript, or Three.js code to the user, the Agent MUST run an internal verification pass against all 12 gates below. If any gate fails, refactor the code silently before delivering the response.

| Gate ID | Audit Dimension | Deterministic Pass / Fail Criterion |
| :--- | :--- | :--- |
| **GATE-01** | **Zero AI Slop Colors** | Count of `indigo-600`, `purple-900`, `violet-500`, or cyan-to-green `radial-gradient` background blobs or glowing neon card borders equals `0`. |
| **GATE-02** | **Zero Headline Gimmicks** | Count of single italicized words, single gradient-colored words, screaming uppercase eyebrow pills, em-dash abuse (`—`), or raw emojis (`🚀`, `🔥`, `✨`) equals `0`. |
| **GATE-03** | **Optical Typography & Tabular Nums** | Display text (`> 28px`) uses tight negative tracking (`-0.018em` to `-0.04em`), body text (`17px`) uses `+0.008em`, and all numerical counters/specs enforce `tabular-nums`. |
| **GATE-04** | **Momentum-Earned Spring Hygiene** | Tap-driven UI and camera transitions use critically damped springs ($\zeta = 1.0$, $\text{bounce} = 0.0$); only gesture release handoffs or 3D blooms use underdamped springs ($\zeta \in [0.7, 0.85]$). Zero `ease-in` curves on entrances. |
| **GATE-05** | **1:1 Pointer Tracking & Velocity Handoff** | Draggable 3D stages and sheets track pointer movement $1:1$ via `setPointerCapture` and hand off release velocity from a rolling timestamp buffer into `AppleSpring` with zero seam. |
| **GATE-06** | **Mid-Flight Interruptibility** | Zero `pointer-events: none` locks during transitions; retargeting any control updates `targetValue` from the live presentation state without jumping. |
| **GATE-07** | **Immediate Pointer-Down Response** | Every interactive button and swatch responds on `:active` (`pointerdown`) within `60ms–80ms` with `transform: scale(0.97)` and implements all 8 interactive states. |
| **GATE-08** | **Continuous Superellipse Curvature** | All Bento cards, floating glass panels, and 3D hardware meshes use G2/G3 squircle geometry (`corner-shape: squircle`, SVG superellipse paths, or `create3DSquircleChassisGeometry`) rather than harsh circular junctions or unchamfered boxes. |
| **GATE-09** | **Studio PBR & Kinetic Lighting** | Three.js renderer enforces `THREE.ACESFilmicToneMapping`, `Math.min(window.devicePixelRatio, 2)`, procedural softbox `PMREMGenerator` reflections, `MeshPhysicalMaterial` (clearcoat/anisotropy/transmission), and animated sweeping Key + Rim lights. |
| **GATE-10** | **Cascading Exploded Choreography** | Multi-part 3D hardware breakdowns use staggered local progress windows (`revealAt`, `revealSpan` across `82%` of the timeline) rather than uniform `[0, 1]` translation. |
| **GATE-11** | **Liquid Glass & Anti-Vection Comfort** | Floating UI overlays use dark translucent glass (`backdrop-filter: saturate(180%) blur(24px)`), `1px` specular borders, never stack light-on-light blur, and reduce opacity during rapid camera movements. |
| **GATE-12** | **Accessibility Media Queries** | `@media (prefers-reduced-motion: reduce)`, `@media (prefers-reduced-transparency: reduce)`, and `@media (prefers-contrast: more)` are wired into both CSS and the JavaScript/WebGL render loop. |