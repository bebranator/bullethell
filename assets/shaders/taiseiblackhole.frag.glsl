#version 330 core

const float pi = 3.1415;
const float R = 35.;
const float inv_aspect_ratio = 1. / 1.;

varying vec2 v_texCoords;
varying vec4 v_color;

//uniform float u_time;
uniform vec2 u_bg_trans, u_mouse, u_resolution;
uniform sampler2D u_texture, u_texture0, u_blend_mask;

const float bmin = 3.0 * sqrt(3.0) / 2.0;

// approximate formula for the real thing I pulled out of my sleeve.
// b is the impact parameter (google) all units are in terms of
// Schwarzschild radii
float bending(float b) {
    float x = b - bmin;
    float a = 2.5;
    float c = 1.136;
    float d = 0.405;
    return pi + 2/b - a*log(tanh(c*pow(x,d)));
}

float angle(vec2 v) {
    return atan(v.y, v.x);
}

vec2 dir(float a) {
    return vec2(cos(a), sin(a));
}

mat2 rot(float a) {
    return mat2(cos(a), -sin(a), sin(a), cos(a));
}

void main(void) {
    vec2 center = u_mouse.xy;
    vec2 d = (v_texCoords * u_resolution) - center;
    d.y *= inv_aspect_ratio;

    float b = length(d)/R;
    if(b < bmin) {
        discard;
    }

    float theta = angle(d);

    float phi = bending(b);

    vec3 n = vec3(0);
    n.zx = dir(phi);
    n.xy = rot(theta)*n.xy;

    float o = 0.1;

    float step0 = smoothstep(0.2, 0.0, n.x) + smoothstep(-0.6, 0, n.z);
    float step1 = smoothstep(0.2, 0.0, n.x + o) + smoothstep(-0.6, 0, n.z + o);

    float antialiasing = smoothstep(bmin,bmin+0.03,b);


    n.xy += u_bg_trans;

//    n.x = mod(u_bg_trans.x, 12);
//    n.y += u_time / 60.;
    n.x = mod(n.x, 0.92);
//    n.y = mod(u_bg_trans.y, 2);

    vec4 tex1c = texture(u_texture, n.xy+vec2(0.5,0));
    vec4 tex2c = texture(u_texture0, n.xy+vec2(0.5));
    float blendfac = texture(u_blend_mask, n.xy).r;

    gl_FragColor = v_color * mix(tex1c, tex2c, clamp(mix(step1, step0, blendfac), 0, 1));
    gl_FragColor.rgb *= antialiasing;
}
