#version 330 core

const float pi = 3.1416;
const float bmin = 3.0 * sqrt(3.0) / 2.0;
const float R = 24.;

// tex2 - blendmask
uniform sampler2D u_texture;

uniform vec2 u_mouse;
uniform vec2 u_resolution;
uniform float u_time;
uniform float u_timemod;

varying vec4 v_color;
varying vec4 v_mix_color;
varying vec2 v_texCoords;

//out vec4 fragColor, in vec2 fragCoord
void main()
{
    // Normalized pixel coordinates (from 0 to 1)
    vec2 uv = v_texCoords;

    float time = u_time * u_timemod;
    vec2 mouse = u_mouse / u_resolution;
    vec2 offset = vec2(cos(time), sin(time));
    vec2 position = -1.0 + 2.0 * uv + (mouse * offset);

//    vec2 position = u_mouse.xy / u_resolution.xy * uv;
    float a = atan( position.y, position.x );
    float r = sqrt( dot( position, position ) );

    vec2 uv2;
    uv2.x = cos( a ) / r;
    uv2.y = sin( a ) / r;
    uv2 /= 10.0;
    uv2 += time;
    uv2 = fract(uv2 / 2);

    vec3 color = texture( u_texture, uv2 ).rgb;


    // Time varying pixel color
    vec3 col = vec3( color * r * 1.5 );

    // Output to screen
    gl_FragColor = vec4(col,1.0);
}
